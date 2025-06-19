package com.example.employeeanalyzer.service;

import com.example.employeeanalyzer.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeAnalyzerService {

    private Map<String, Employee> employeeMap = new HashMap<>();
    private Employee ceo;

    public void loadEmployees(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();
                String managerId = parts[2].trim().isEmpty() ? null : parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                Employee emp = new Employee(id, name, managerId, salary);
                employeeMap.put(id, emp);
            }
        }

        for (Employee emp : employeeMap.values()) {
            if (emp.managerId == null) {
                ceo = emp;
            } else {
                Employee manager = employeeMap.get(emp.managerId);
                if (manager != null) {
                    manager.subordinates.add(emp);
                }
            }
        }
    }

    public void analyzeSalaries() {
        for (Employee manager : employeeMap.values()) {
            if (manager.subordinates.isEmpty()) continue;

            double total = 0;
            for (Employee sub : manager.subordinates) {
                total += sub.salary;
            }
            double avg = total / manager.subordinates.size();
            double minRequired = avg * 1.2;
            double maxAllowed = avg * 1.5;

            if (manager.salary < minRequired) {
                System.out.printf("Manager %s earns too little. Short by %.2f%n", manager.name, minRequired - manager.salary);
            } else if (manager.salary > maxAllowed) {
                System.out.printf("Manager %s earns too much. Exceeds by %.2f%n", manager.name, manager.salary - maxAllowed);
            }
        }
    }

    public void analyzeReportingDepth() {
        dfsDepth(ceo, 0);
    }

    private void dfsDepth(Employee emp, int depth) {
        if (depth > 4) {
            System.out.printf("Employee %s has a reporting line too long. Exceeds by %d levels%n", emp.name, depth - 4);
        }

        for (Employee sub : emp.subordinates) {
            dfsDepth(sub, depth + 1);
        }
    }
}

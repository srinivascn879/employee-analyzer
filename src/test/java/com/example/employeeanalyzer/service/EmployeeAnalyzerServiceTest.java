package com.example.employeeanalyzer.service;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAnalyzerServiceTest {


    @Test
    void testLoadEmployees() throws IOException {
        EmployeeAnalyzerService employeeAnalyzerService = new EmployeeAnalyzerService();
        employeeAnalyzerService.loadEmployees("src/main/resources/employees.csv");
        assertNotNull(employeeAnalyzerService);
    }

    @Test
    void testAnalyzeSalariesAndDepth() throws IOException {
        EmployeeAnalyzerService employeeAnalyzerService = new EmployeeAnalyzerService();
        employeeAnalyzerService.loadEmployees("src/main/resources/employees.csv");
        employeeAnalyzerService.analyzeSalaries();
        employeeAnalyzerService.analyzeReportingDepth();
    }
}

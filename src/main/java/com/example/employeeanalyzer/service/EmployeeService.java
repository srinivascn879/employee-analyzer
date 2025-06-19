package com.example.employeeanalyzer.service;

import com.example.employeeanalyzer.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeAnalyzerService employeeAnalyzerService;

  public void employeeAnalyzer() {
      try {
          employeeAnalyzerService.loadEmployees("src/main/resources/employees.csv");
          System.out.println("Salary Analysis:");
          employeeAnalyzerService.analyzeSalaries();
          System.out.println("\nReporting Line Analysis:");
          employeeAnalyzerService.analyzeReportingDepth();
      } catch (Exception ex) {
          ex.printStackTrace();
      }
  }
}

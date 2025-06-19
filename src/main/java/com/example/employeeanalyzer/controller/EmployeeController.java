package com.example.employeeanalyzer.controller;

import com.example.employeeanalyzer.dto.Employee;
import com.example.employeeanalyzer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/analyzer")
    public void getEmployee() {
        employeeService.employeeAnalyzer();
    }


}

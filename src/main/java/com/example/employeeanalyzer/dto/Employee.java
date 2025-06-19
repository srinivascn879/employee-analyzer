package com.example.employeeanalyzer.dto;

import java.util.*;

public class Employee {

    public String id;
    public String name;
    public String managerId;
    public double salary;
    public List<Employee> subordinates = new ArrayList<>();

    public Employee(String id, String name, String managerId, double salary) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.salary = salary;
    }
}

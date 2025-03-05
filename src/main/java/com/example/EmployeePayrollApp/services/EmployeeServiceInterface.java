package com.example.EmployeePayrollApp.services;

import com.example.EmployeePayrollApp.model.Employee;
import java.util.List;

public interface EmployeeServiceInterface {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee addEmployee(Employee emp);
    Employee updateEmployee(Long id, Employee emp);
    void deleteEmployee(Long id);
}

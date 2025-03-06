package com.example.EmployeePayrollApp.services;

import com.example.EmployeePayrollApp.dto.EmployeePayrollIDTO;
import com.example.EmployeePayrollApp.model.Employee;
import java.util.List;

public interface EmployeeServiceInterface {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee addEmployee(EmployeePayrollIDTO emp);
    Employee updateEmployee(Long id, EmployeePayrollIDTO emp);
    void deleteEmployee(Long id);
}

package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.EmployeePayrollIDTO;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.services.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeServiceInterface employeeServiceInterface;

    @GetMapping("/")
    public List<Employee> getAll() {
        logger.info("Fetching all employees...");
        List<Employee> employees = employeeServiceInterface.getAllEmployees();
        logger.info("Total employees retrieved: {}", employees.size());
        return employees;
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable Long id) {
        logger.info("Fetching employee with ID: {}", id);
        Employee employee = employeeServiceInterface.getEmployeeById(id);
        if (employee != null) {
            logger.info("Employee found: {}", employee);
        } else {
            logger.warn("Employee with ID {} not found!", id);
        }
        return employee;
    }

    @PostMapping("/create")
    public Employee add(@RequestBody EmployeePayrollIDTO emp) {
        logger.info("Adding new employee: {}", emp);
        Employee employee = employeeServiceInterface.addEmployee(emp);
        logger.info("Employee added successfully with ID: {}", employee.getId());
        return employee;
    }

    @PutMapping("/update/{id}")
    public Employee update(@PathVariable Long id, @RequestBody EmployeePayrollIDTO emp) {
        logger.info("Updating employee with ID: {}", id);
        Employee updatedEmployee = employeeServiceInterface.updateEmployee(id, emp);
        logger.info("Employee updated: {}", updatedEmployee);
        return updatedEmployee;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        logger.warn("Deleting employee with ID: {}", id);
        employeeServiceInterface.deleteEmployee(id);
        logger.info("Employee with ID {} deleted successfully", id);
    }
}
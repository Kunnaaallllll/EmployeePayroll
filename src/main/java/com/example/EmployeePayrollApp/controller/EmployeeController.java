package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.EmployeePayrollIDTO;
import com.example.EmployeePayrollApp.exception.EmployeeNotFoundException;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.services.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeServiceInterface employeeServiceInterface;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAll() {
        logger.info("Fetching all employees...");
        List<Employee> employees = employeeServiceInterface.getAllEmployees();
        logger.info("Total employees retrieved: {}", employees.size());
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        logger.info("Fetching employee with ID: {}", id);
        Employee employee = employeeServiceInterface.getEmployeeById(id);
        if (employee != null) {
            logger.info("Employee found: {}", employee);
            return ResponseEntity.ok(employee);
        } else {
            logger.warn("Employee with ID {} not found!", id);
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> add(@RequestBody EmployeePayrollIDTO emp) {
        logger.info("Adding new employee: {}", emp);
        Employee employee = employeeServiceInterface.addEmployee(emp);
        logger.info("Employee added successfully with ID: {}", employee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody EmployeePayrollIDTO emp) {
        logger.info("Updating employee with ID: {}", id);
        Employee updatedEmployee = employeeServiceInterface.updateEmployee(id, emp);
        if (updatedEmployee == null) {
            logger.warn("Employee with ID {} not found for update!", id);
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        logger.info("Employee updated: {}", updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        logger.warn("Deleting employee with ID: {}", id);
        employeeServiceInterface.deleteEmployee(id);
        logger.info("Employee with ID {} deleted successfully", id);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
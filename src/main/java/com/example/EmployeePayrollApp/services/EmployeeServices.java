package com.example.EmployeePayrollApp.services;

import com.example.EmployeePayrollApp.dto.EmployeePayrollIDTO;
import com.example.EmployeePayrollApp.exception.EmployeeNotFoundException;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.repositries.EmployeeRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class EmployeeServices implements EmployeeServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServices.class);

    @Autowired
    private EmployeeRepositories repository;

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees...");
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            logger.warn("No employees found in the database!");
            throw new EmployeeNotFoundException("No employees available");
        }
        logger.info("Total employees retrieved: {}", employees.size());
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee with ID {} not found!", id);
                    return new EmployeeNotFoundException("Employee with ID " + id + " not found");
                });
    }

    @Override
    public Employee addEmployee(EmployeePayrollIDTO emp) {
        logger.info("Adding new employee: {}", emp);
        Employee employee = new Employee();
        employee.setName(emp.getName());
        employee.setEmail(emp.getEmail());
        employee.setSalary(emp.getSalary());
        employee.setDepartment(emp.getDepartment());

        Employee savedEmployee = repository.save(employee);
        logger.info("Employee added successfully with ID: {}", savedEmployee.getId());
        return savedEmployee;
    }

    @Override
    public Employee updateEmployee(Long id, EmployeePayrollIDTO emp) {
        logger.info("Updating employee with ID: {}", id);
        Employee existingEmp = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee with ID {} not found, update failed!", id);
                    return new EmployeeNotFoundException("Employee with ID " + id + " not found");
                });

        existingEmp.setName(emp.getName());
        existingEmp.setSalary(emp.getSalary());
        existingEmp.setDepartment(emp.getDepartment());
        existingEmp.setEmail(emp.getEmail());

        Employee updatedEmployee = repository.save(existingEmp);
        logger.info("Employee updated successfully: {}", updatedEmployee);
        return updatedEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        logger.warn("Deleting employee with ID: {}", id);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee with ID {} not found, delete failed!", id);
                    return new EmployeeNotFoundException("Employee with ID " + id + " not found");
                });

        repository.deleteById(id);
        logger.info("Employee with ID {} deleted successfully", id);
    }
}
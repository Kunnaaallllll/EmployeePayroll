package com.example.EmployeePayrollApp.services;

import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.repositries.EmployeeRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices implements EmployeeServiceInterface{

    @Autowired
    private EmployeeRepositories repository;

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Employee addEmployee(Employee emp) {
        return repository.save(emp);
    }

    @Override
    public Employee updateEmployee(Long id, Employee emp) {
        Employee existingEmp = repository.findById(id).orElse(null);
        if (existingEmp != null) {
            existingEmp.setName(emp.getName());
            existingEmp.setSalary(emp.getSalary());
            return repository.save(existingEmp);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

}

package com.example.EmployeePayrollApp.services;

import com.example.EmployeePayrollApp.dto.EmployeePayrollIDTO;
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
    public Employee addEmployee(EmployeePayrollIDTO emp) {
        Employee employee=new Employee();
        employee.setName(emp.getName());
        employee.setDepartment(emp.getDepartment());
        employee.setEmail(emp.getEmail());
        employee.setSalary(emp.getSalary());
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeePayrollIDTO emp) {
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

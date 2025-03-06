package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.EmployeePayrollIDTO;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.services.EmployeeServiceInterface;
import com.example.EmployeePayrollApp.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    private EmployeeServiceInterface serviceInterface;

    @GetMapping("/")
    public List<Employee> getAll() {
        return serviceInterface.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable Long id) {
        return serviceInterface.getEmployeeById(id);
    }

    @PostMapping("/create")
    public Employee add(@RequestBody EmployeePayrollIDTO emp) {
        return serviceInterface.addEmployee(emp);
    }

    @PutMapping("/update/{id}")
    public Employee update(@PathVariable Long id, @RequestBody EmployeePayrollIDTO emp) {
        return serviceInterface.updateEmployee(id, emp);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        serviceInterface.deleteEmployee(id);
    }
}

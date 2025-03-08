package com.example.EmployeePayrollApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EmployeePayrollIDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Email
    private String email;

    @NotBlank(message = "Salary Cannot be Empty")
    private double salary;

    @NotBlank(message = "Department cannot be empty")
    private String department;
}
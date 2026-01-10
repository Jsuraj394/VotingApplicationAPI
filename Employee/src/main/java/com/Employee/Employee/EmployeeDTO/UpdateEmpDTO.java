package com.Employee.Employee.EmployeeDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateEmpDTO {
    @Size(min=2, message = "Name must be at least 2 characters long")
    private String name;
    @Min(value = 1000, message = "Salary must be at least 1000")
    private Double Salary;
}

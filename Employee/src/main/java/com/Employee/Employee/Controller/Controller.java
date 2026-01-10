package com.Employee.Employee.Controller;


import com.Employee.Employee.EmployeeDTO.UpdateEmpDTO;
import com.Employee.Employee.Entity.Employee;
import com.Employee.Employee.ExceptionHandler.EmpAlreadyExistException;
import com.Employee.Employee.ExceptionHandler.EmpDoesNotExistException;
import com.Employee.Employee.ExceptionHandler.ErrorResponse;
import com.Employee.Employee.Services.EmployeeServices;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees/")
public class Controller {
    @Autowired
    EmployeeServices empServ;
    public Controller(EmployeeServices empServ) {
        this.empServ = empServ;
    }

    @PostMapping("/add")
    public String addEmp( @Valid @RequestBody Employee emp){
        empServ.addEmployee(emp);
        return "Employee added successfully";
    }
    @PutMapping("/update/{id}")
    public String updateEmp(@Valid @RequestBody UpdateEmpDTO emp, @PathVariable("id") int id) {
        empServ.updateEmployee(emp,id);
        return "Employee updated successfully";
    }

    @PostMapping("/delete/{empid}")
    public String deleteEmp(@PathVariable("empid") int empid) {
        empServ.deleteEmployee(empid);
        return "Employee deleted successfully";
    }
    @GetMapping("/get/{id}")
    public Employee getEmpById(@PathVariable("id") int id) {
        return empServ.getEmployeeById(id);
    }




}

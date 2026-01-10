package com.Employee.Employee.Services;


import com.Employee.Employee.EmployeeDTO.UpdateEmpDTO;
import com.Employee.Employee.Entity.Employee;
import com.Employee.Employee.ExceptionHandler.EmpAlreadyExistException;
import com.Employee.Employee.ExceptionHandler.EmpDoesNotExistException;
import com.Employee.Employee.Repository.EmpRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {


    public EmployeeServices(EmpRepository repo) {
        this.repo = repo;
    }

    private EmpRepository repo;

    public String addEmployee(Employee emp){
        Employee e  = repo.findByName(emp.getName()).orElse(null);
        if (e != null) {
            throw new EmpAlreadyExistException("This Employee is already exists in Database");
        }
        repo.save(emp);
        return "Employee added successfully";
    }


    public String updateEmployee(UpdateEmpDTO emp, Integer id) {
        Employee e =  repo.findById(id).orElse(null);
        if (e != null && e.getId().equals(id)) {
            if(emp.getSalary() == null && emp.getName() == null){
                throw new IllegalArgumentException("At least one field (name or salary) must be provided for update");
            }

            if(emp.getName()!=null) {
                e.setName(emp.getName());
            }
            if(emp.getSalary()!=null) {
                e.setSalary(emp.getSalary());
            }
            repo.save(e);
            return "Employee updated successfully";
        }
        else{
            throw new EmpDoesNotExistException("This Employee is Not exists in Database");
        }
    }

    public String deleteEmployee(Integer empid) {
        Employee e =  repo.findById(empid).orElse(null);
        if (e != null && e.getId().equals(empid)) {
            repo.delete(e);
            return "Employee deleted successfully";
        }
        else{
            throw new EmpDoesNotExistException("This Employee is Not exists in Database");
        }
    }

    public Employee getEmployeeById(int id) {
        Employee e = repo.findById(id).orElse(null);
        if (e != null && e.getId().equals(id)) {
            return e;
        }
        else{
            throw new EmpDoesNotExistException("This Employee is Not exists in Database");
        }
    }
}

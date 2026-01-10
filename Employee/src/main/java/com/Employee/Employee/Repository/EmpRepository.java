package com.Employee.Employee.Repository;


import com.Employee.Employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByName(String name);
}

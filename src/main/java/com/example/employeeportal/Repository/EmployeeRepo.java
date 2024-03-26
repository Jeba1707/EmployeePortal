package com.example.employeeportal.Repository;

import com.example.employeeportal.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
}

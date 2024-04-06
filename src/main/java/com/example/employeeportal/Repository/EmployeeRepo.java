package com.example.employeeportal.Repository;

import com.example.employeeportal.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

    public List<EmployeeEntity> findByEmployeeName(String employeeName);

    public List<EmployeeEntity> findAllBySalaryEquals(Long salary);

    @Query("select e from EmployeeEntity e where e.salary =:n")
    public List<EmployeeEntity> getEmployeesBySalary(@Param("n") Long salary);
}

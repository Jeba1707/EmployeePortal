package com.example.employeeportal.Controller;

import com.example.employeeportal.DTO.EmployeeDTO;
import com.example.employeeportal.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeportal")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //url : localhost:8070/employeeportal/addEmployee
    @PostMapping("/addEmployee")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.addEmployee(employee);
    }

    //url : localhost:8070/employeeportal/employlist
    @GetMapping("/employlist")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //url : localhost:8070/employeeportal/employ/2
    @GetMapping("/employ/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    //url : localhost:8070/employeeportal/updateEmployee/3
    @PutMapping("/updateEmployee/{id}")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employee, @PathVariable Long id){
        return employeeService.updateEmployee(employee,id);
    }

    //localhost:8070/employeeportal/deleteEmployee/4
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
//           A JSON
//        {
//        "name": "Ratno",
//        "age": 21,
//        "salary": 30500
//        }
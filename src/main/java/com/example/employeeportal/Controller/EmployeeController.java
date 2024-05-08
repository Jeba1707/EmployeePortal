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
   // localhost:8070/employeeportal/employlist?page=0&size=4&sort=asc
    @GetMapping("/employlist")
    public List<EmployeeDTO> getAllEmployees(@RequestParam(defaultValue = "0",required = false) Integer page,@RequestParam(defaultValue = "3",required = false) Integer size,@RequestParam(defaultValue = "asc",required = false) String sort) {
        return employeeService.getAllEmployees(page,size,sort);
    }

    //url : localhost:8070/employeeportal/employ/2
    @GetMapping("/employ/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    //url: localhost:8070/employeeportal/employeeByName?name=Jeba
    @GetMapping("/employeeByName")
    public List<EmployeeDTO> getEmployeeByName(@RequestParam("name") String name){
        return employeeService.getEmployeeByName(name);
    }

    //url : localhost:8070/employeeportal/employeeBySalary?salary=20000
    @GetMapping("/employeeBySalary")
    public List<EmployeeDTO> getEmployeeBySalary(@RequestParam("salary") Long salary){
        return employeeService.getEmployeeBySalaryEqualsTo(salary);
    }

    //url : localhost:8070/employeeportal/employBySalary?salary=19000
    @GetMapping("/employBySalary")
    public List<EmployeeDTO> getEmployBySalary(@RequestParam("salary") Long salary){
        return employeeService.getEmployeeBySalary(salary);
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
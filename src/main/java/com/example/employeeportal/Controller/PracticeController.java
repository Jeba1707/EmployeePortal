package com.example.employeeportal.Controller;


//import com.example.employeeportal.DTO.EmployeeDTO;
import com.example.employeeportal.DTO.EmployeeDTO;
import com.example.employeeportal.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PracticeController {

    @Autowired
    EmployeeService employeeService;
    // http://localhost:8070/hello
    @RequestMapping("/hello")
    public String hello(Model model) {
      List<EmployeeDTO> employees =  employeeService.getAllEmployees(0,15,"asc");
        model.addAttribute("employees", employees);
         model.addAttribute("message", "Hello World! from java");
        return "index.html";
    }

    @GetMapping("/form")
    public String form(Model model) {
       model.addAttribute("employee", new EmployeeDTO());
        return "registerform.html";
    }

    @PostMapping("/successfull")
    public String successfull(@ModelAttribute("employeed") EmployeeDTO employeeDTO,Model model) {
        employeeService.addEmployee(employeeDTO);
        model.addAttribute("employee", employeeDTO);
        return "successfull.html";
    }
}

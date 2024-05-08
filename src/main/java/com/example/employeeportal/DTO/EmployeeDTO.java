package com.example.employeeportal.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String name;
    private int age;
    private long salary;
    private String address;
}

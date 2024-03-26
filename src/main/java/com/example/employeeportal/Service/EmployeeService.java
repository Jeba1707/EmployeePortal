package com.example.employeeportal.Service;

import com.example.employeeportal.DTO.EmployeeDTO;
import com.example.employeeportal.Entities.EmployeeEntity;
import com.example.employeeportal.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO){
     saveEntity(employeeDTO);
     return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<EmployeeEntity> employeeEntityList = employeeRepo.findAll();
        for(EmployeeEntity employee:employeeEntityList){
           employeeDTOS.add(mapDto(employee));
        }
        return employeeDTOS;
    }

    public EmployeeDTO getEmployeeById(Long id){
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepo.findById(id);
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
        if(employeeEntity==null){
         throw new RuntimeException("Employee not found");

        }
        return mapDto(employeeEntity);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO,Long id){
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepo.findById(id);
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
        if(employeeEntity==null){
            throw new RuntimeException("Employee not found");

        }
        employeeEntity.setEmployeeName(employeeDTO.getName());
        employeeEntity.setAge(employeeDTO.getAge());
        employeeEntity.setSalary(employeeDTO.getSalary());
        employeeRepo.save(employeeEntity);
        return mapDto(employeeEntity);

    }

    public void deleteEmployee(Long id){
      Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepo.findById(id);
      EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
      if(employeeEntity==null){
          throw new RuntimeException("Employee not found");

      }
      employeeRepo.delete(employeeEntity);
    }
    public EmployeeDTO mapDto(EmployeeEntity employeeEntity){
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeEntity.getEmployeeName(),employeeEntity.getAge(),employeeEntity.getSalary());
        return employeeDTO;

    }

        public  EmployeeEntity saveEntity(EmployeeDTO employeeDTO){
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setEmployeeName(employeeDTO.getName());
            employeeEntity.setAge(employeeDTO.getAge());
            employeeEntity.setSalary(employeeDTO.getSalary());
           return employeeRepo.save(employeeEntity);

    }
}

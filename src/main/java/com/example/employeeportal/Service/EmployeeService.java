package com.example.employeeportal.Service;

import com.example.employeeportal.DTO.EmployeeDTO;
import com.example.employeeportal.Entities.EmployeeEntity;
import com.example.employeeportal.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<EmployeeDTO> getAllEmployees(Integer page,Integer size,String sortOrder){
        Sort sort = sortOrder.equals("asc") ? Sort.by("employeeName").ascending(): Sort.by("employeeName").descending();
        Pageable paging = PageRequest.of(page,size,sort);
        Page<EmployeeEntity> pageContent = employeeRepo.findAll(paging);
        List<EmployeeEntity> all = pageContent.getContent();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(EmployeeEntity employee:all){
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

    public List<EmployeeDTO> getEmployeeByName(String name){
        List<EmployeeEntity> list = employeeRepo.findByEmployeeName(name);
        List<EmployeeDTO> dtolist = new ArrayList<>();
        list.forEach(e->dtolist.add(mapDto(e)));

        return dtolist;
    }

    public List<EmployeeDTO> getEmployeeBySalaryEqualsTo(Long salary){
        List<EmployeeEntity> list = employeeRepo.findAllBySalaryEquals(salary);
        List<EmployeeDTO> dtolist = new ArrayList<>();
        list.forEach(e->dtolist.add(mapDto(e)));

        return dtolist;
    }

    public List<EmployeeDTO> getEmployeeBySalary(Long salary){
        List<EmployeeEntity> list = employeeRepo.getEmployeesBySalary(salary);
        List<EmployeeDTO> dtolist = new ArrayList<>();
        list.forEach(e->dtolist.add(mapDto(e)));

        return dtolist;
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
        employeeEntity.setAddress(employeeDTO.getAddress());
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
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeEntity.getEmployeeName(),employeeEntity.getAge(),employeeEntity.getSalary(), employeeEntity.getAddress());
        return employeeDTO;

    }

        public  EmployeeEntity saveEntity(EmployeeDTO employeeDTO){
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setEmployeeName(employeeDTO.getName());
            employeeEntity.setAge(employeeDTO.getAge());
            employeeEntity.setSalary(employeeDTO.getSalary());
            employeeEntity.setAddress(employeeDTO.getAddress());
           return employeeRepo.save(employeeEntity);

    }
}

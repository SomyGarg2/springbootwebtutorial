package com.codingshuffle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message: dsasdsdjfd";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path="/{employeeID}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeID") Long id) {
      //  return new EmployeeDTO(id, "Somy", "somy@gmail.com", 21, LocalDate.of(2025, 8, 12), true);
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping(path="")
        public String allEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
         return "Hi age " + age + " " + sortBy;
        }

        @PostMapping
       public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;
        }

        @PutMapping String updateEmployeeById(){
        return "Hello from Put";
        }
}
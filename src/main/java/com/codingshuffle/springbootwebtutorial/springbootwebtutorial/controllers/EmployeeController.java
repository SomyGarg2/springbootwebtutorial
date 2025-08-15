package com.codingshuffle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
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

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    @GetMapping(path="/{employeeID}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeID") Long id) {
      //  return new EmployeeDTO(id, "Somy", "somy@gmail.com", 21, LocalDate.of(2025, 8, 12), true);
        //return employeeRepository.findById(id).orElse(null);
        return employeeService.getEmployeeById(id);

    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(
            @RequestParam(required = false, name = "inputAge") Integer age,
            @RequestParam(required = false) String sortBy) {
        return employeeService.getAllEmployees();
    }


        @PostMapping
        public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
            return employeeService.createNewEmployee(inputEmployee);
        }
//       public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
//        }

        @PutMapping String updateEmployeeById(){
        return "Hello from Put";
        }
}
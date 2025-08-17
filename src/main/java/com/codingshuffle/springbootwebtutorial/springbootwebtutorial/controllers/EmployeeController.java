package com.codingshuffle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeID") Long id) {
      //  return new EmployeeDTO(id, "Somy", "somy@gmail.com", 21, LocalDate.of(2025, 8, 12), true);
        //return employeeRepository.findById(id).orElse(null);
      Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
      return employeeDTO
              .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).
              orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(
            @RequestParam(required = false, name = "inputAge") Integer age,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }


        @PostMapping
        public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }
//       public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
//        }

        @PutMapping(path = "/{employeeId}")
       public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
        }

        @DeleteMapping(path = "/{employeeId}")
        public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
            boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
            if (gotDeleted) return ResponseEntity.ok(true);
            return ResponseEntity.notFound().build();
        }

        @PatchMapping(path = "/{employeeId}")
        public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
           EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId,updates);
           if (employeeDTO == null) return ResponseEntity.notFound().build();
           return ResponseEntity.ok(employeeDTO);
        }

}
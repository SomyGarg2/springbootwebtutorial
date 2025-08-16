package com.codingshuffle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingshuffle.springbootwebtutorial.springbootwebtutorial.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
   private Long id;

   @NotNull(message = "Required field in Employee: name")
   @NotEmpty(message = "Name of the Employee cannot be Empty")
   @NotBlank(message = "Name of the Employee cannot be Blank")
   @Size(min = 3,max = 10, message = "Number of characters in name should be in range : [3,10]")
    private String name;

    @NotBlank(message = "Email of the Employee cannot be Blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    @NotBlank(message = "role of the Employee cannot be Blank")
  //  @Pattern(regexp = "^(ADMIN|USER)$", message = "The role of employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; // ADMIN, USER

    @NotNull(message = "Salary should be not null")
    @Positive(message = "Salary should be Positive")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form of XXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.58")
    private Double salary;


    @PastOrPresent(message = "dateOfJoining field in employee cannot be in future")
    private LocalDate dateOfJoining;


    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;


}

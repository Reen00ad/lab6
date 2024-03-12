package com.example.lab6.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 2,message = "id length should be more than 2 ")
    private String id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 4,message = "name length should be more than 4 ")
    @Pattern(regexp = "^[A-Za-z]+$",message = "name must only character")
    private String name;
    @Email(message = "enter valid email")
    private String email;
    @Pattern(regexp ="^05?[0-9]+$", message = "phone number must start with 05")
    @Size(min = 10,max = 10,message = "number must be 10 diget")
    private String phonenumber;
    @NotNull(message = "age should not be null")
    //@Pattern(regexp = "^[0-9]+$",message = "age must be a number")
    @Min(value = 25,message = "age must be greater than 25")
    private Integer age;
    @NotEmpty(message = "position should not be null")
    @Pattern(regexp ="^(supervisor|coordinator)$", message = "position must be supervisor or coordinator only")
    private String position;
    @AssertFalse
    private Boolean onleav;
    @NotEmpty(message = "hiredate should not be null")
    //@PastOrPresent
    @Min(1900)
    private String hiredate;
    @NotNull(message = "annual leave should not be null")
    //@Pattern(regexp = "[0-9]*$",message = "must be positive number")
    private Integer annualleave;

}

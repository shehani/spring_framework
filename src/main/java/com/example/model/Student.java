package com.example.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Student {
    @Email(message="Invalid Email, Please enter a correct email address")
    private String email;
}

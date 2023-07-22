package com.example.validations;

import com.example.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator,String> {
    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator passwordValidator){
        weakPasswords = Arrays.asList("1234","1qaz2wsX@","password");
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }


}

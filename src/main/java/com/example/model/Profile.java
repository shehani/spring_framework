package com.example.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class Profile {


    @NotBlank(message = "Name must not be blank")
    @Size(min=3,message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "^$|[0-9]{10}",message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @NotBlank(message = "confirm email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    private String email;
    @NotBlank(message = "Address must not be blank")
    private String address;
    private String address2;
    @NotBlank(message = "City must not be blank")
    @Size(min = 5, message = "city length must be at least 5 characters long")
    private String city;
    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "state length must be at least 5 characters long")
    private String state;
    @NotBlank(message = "ZipCode must not be blank")
    @Size(min = 5, max = 5 , message = "zip code size should be 5 length")
    private String zipCode;

}

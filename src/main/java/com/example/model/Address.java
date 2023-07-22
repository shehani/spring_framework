package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")
    private int addressId;

    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5, message = "address length must be at least 5 characters long")
    private String address1;
    private String address2;

    @NotBlank(message = "City must not be blank")
    @Size(min = 5, message = "city length must be at least 5 characters long")
    private String city;
    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "state length must be at least 5 characters long")
    private String state;
    @NotBlank(message = "ZipCode must not be blank")
    @Size(min = 5, max = 5 , message = "zip code size should be 5 digits")
    private int zipCode;

}

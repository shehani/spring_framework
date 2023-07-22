package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
public class Idea extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native" , strategy = "native")
    private int id;

    @Column(name= "firstname")
    @NotBlank(message = "First Name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Invalid First Name")
    private String firstName;

    @Column(name= "lastname")
    @NotBlank(message = "Last Name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Invalid Last Name")
    private String lastName;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "Address2 must not be blank")
    private String address2;

    @NotBlank(message = "Idea must not be blank")
    @Size(min=10,message = "Idea must be more descriptive")
    private String opinion;

    private String status;




}

package com.example.model;

import com.example.annotation.FieldsValueMatch;
import com.example.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "confirmedPassword",
                message = "Passwords do not match"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmedEmail",
                message = "Email addresses do not match!"
        )
        })
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min=3,message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "^$|[0-9]{10}",message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @NotBlank(message = "confirm email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    private String email;
    @Transient
    private String confirmedEmail;
    @PasswordValidator
    private String password;
    @Transient
    private String confirmedPassword;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,targetEntity = Role.class)
    @JoinColumn(name= "role_id" , referencedColumnName = "role_Id" , nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id" , referencedColumnName = "addressId" , nullable = true)
    private Address address;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY , optional = true)
    @JoinColumn(name = "church_id", referencedColumnName = "churchId" , nullable = true)
    private Church churchOb;


}

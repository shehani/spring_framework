package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
public class Grade extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")
    private int gradeId;

    @NotBlank(message = "Church Name must not be blank")
    @Size(min = 5 , message = "Please enter valid Church Name, length must be more than 5")
    private String name;

    @ManyToMany(mappedBy = "grades" , fetch = FetchType.EAGER , cascade = CascadeType.PERSIST)
    private Set<Person> persons = new HashSet<>();



}

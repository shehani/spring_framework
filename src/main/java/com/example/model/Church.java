package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Set;


@Entity
@Data
public class Church extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")
    private int churchId;
    @NotBlank(message = "Church Name must not be blank")
    @Size(min = 5 , message = "Please enter valid Church Name")
    private String name;

    @OneToMany(mappedBy = "churchOb" , fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private List<Person> personList;



}

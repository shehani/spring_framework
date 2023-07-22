package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Role extends BaseEntity{
     @Id
     @GeneratedValue(strategy= GenerationType.AUTO,generator = "native")
     @GenericGenerator(name = "native" , strategy = "native")
     private int role_Id;
     private String roleName;


}

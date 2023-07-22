package com.example.repository;

import com.example.model.Church;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Church, Integer> {

}

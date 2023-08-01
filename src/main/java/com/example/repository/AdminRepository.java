package com.example.repository;

import com.example.model.Church;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Church, Integer> {
    @Query(value = "select c from Church c order by c.churchId desc")
    List<Church> findAllChurch();
}

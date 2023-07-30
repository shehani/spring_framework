package com.example.repository;

import com.example.model.Grade;
import com.example.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Integer> {

    List<Grade> findByOrderByNameDesc();

}

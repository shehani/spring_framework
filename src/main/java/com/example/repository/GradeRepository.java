package com.example.repository;

import com.example.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GradeRepository extends JpaRepository<Grade,Integer> {

}

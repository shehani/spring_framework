package com.example.repository;

import com.example.model.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends CrudRepository<Idea,Integer> {
    List<Idea> findByFirstName(String name); // Derived Query Method
    List<Idea> findByStatus(String status); // Derived Query Method


}

package com.example.repository;

import com.example.model.Idea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends CrudRepository<Idea,Integer> {
    List<Idea> findByFirstName(String name); // Derived Query Method
    Page<Idea> findByStatus(String status , Pageable pageable);
    List<Idea> findByStatusOrderByCreatedDateDesc(String status );


}

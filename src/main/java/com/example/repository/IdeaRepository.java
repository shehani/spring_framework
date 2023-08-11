package com.example.repository;

import com.example.model.Idea;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends CrudRepository<Idea,Integer> {
    List<Idea> findByFirstName(String name); // Derived Query Method
    List<Idea> findByStatus(String status); // Derived Query Method

    //Page<Idea> findByStatus(String status , Pageable pageable);

    //below custom queries methods are implemented via @Query where custom queries are written under same repository class as below
    @Query(value = "select * from idea i where i.status = :status",nativeQuery = true)
    Page<Idea> findByStatusPagination(String status , Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update idea i set i.status = :status where i.id = :id ",nativeQuery = true)
    int updateStatus(String status , int id);

    //end
    List<Idea> findByStatusOrderByCreatedDateDesc(String status );



   //below custom queries methods are implemented via @NamedQuery where custom queries are written under Idea Entity class
    //Page<Idea> findStatus(String status , Pageable pageable);

    @Transactional
    @Modifying
    int updateIdeaStatus(String status , int id);

    //below custom queries methods are implemented via @NamedNativeQuery where custom queries are written under Idea Entity class
    @Query(nativeQuery = true)
    Page<Idea> findStatusNative(String status , Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateIdeaStatusNative(String status , int id);

}

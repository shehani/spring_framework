package com.example.repository;

import com.example.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "mass-schedule") // can change spring data rest default path name from schedules to as wish
//@RepositoryRestResource(exported = false) // can be skip spring data rest defining APIs for this repository using exported = false
public interface ScheduleRepository extends CrudRepository<Schedule,Integer> {


}

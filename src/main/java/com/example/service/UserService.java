package com.example.service;

import com.example.model.Person;
import com.example.model.Role;
import com.example.repository.PersonRepository;
import com.example.repository.RoleRepository;
import com.example.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This service is to support user specific business logic ex: register User , update User
 */

@Service
@Slf4j
public class UserService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * This method is to create/register user to the system
     * @param person
     * @return
     */
    public boolean createUser(Person person){
        boolean isSaved = false;
        Role role = roleRepository.findByRoleName(Constant.registerUserRole);
        String pw = person.getPassword();
        if(role != null && role.getRole_Id()>0){
            person.setRole(role);
        }else {
            throw  new RuntimeException("Role is Not Found. Error_Code, ROLE_MISSING");
        }
        person.setPassword(passwordEncoder.encode(pw));
        person =(Person) personRepository.save(person);

        if(person != null && person.getPersonId()>0){
            isSaved = true;
            log.info("New User has been saved into DB , name "+person.getName());
        }
        return isSaved;

    }
}

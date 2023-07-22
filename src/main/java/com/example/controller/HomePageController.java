package com.example.controller;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @Autowired
    PersonRepository personRepository;

    /**
     * This method supports for saving person into session and displaying home page
     * @param authentication
     * @param httpSession
     * @return
     */
    @RequestMapping(path={"","/","home"})
    public String DisplayHomePage(Authentication authentication, HttpSession httpSession){
        Person person = personRepository.findByEmail(authentication.getName());
        httpSession.setAttribute("loggedInUser",person);
        return "index.html";
    }


}

package com.example.controller;

import com.example.model.Person;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is for maintaining endpoints which are public to user ex: registration page
 */
@Controller
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;
    @GetMapping(path = "/register")
    public String displayRegisterPage(Model model){
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping(path = "/createUser")
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors){
        if(errors.hasErrors()){
            return "register.html";
        }
        if(userService.createUser(person)) {
            return "redirect:/login?register=true";
        }else {
            return "register.html";
        }
    }

}

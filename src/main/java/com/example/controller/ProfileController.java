package com.example.controller;

import com.example.model.Address;
import com.example.model.Person;
import com.example.model.Profile;
import com.example.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("profileControllerBean")
@Slf4j
public class ProfileController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(value = "/details")
    public ModelAndView displayProfile(HttpSession httpSession, @RequestParam(value = "updated" , required = false) String updated){
        String error = null;
        if (updated != null) {
            error = "Profile has been updated successfully";

        }
        Person person = (Person) httpSession.getAttribute("loggedInUser");
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress() != null && person.getAddress().getAddressId() > 0){
            profile.setAddress(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(String.valueOf(person.getAddress().getZipCode()));
        }

        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile",profile);
        modelAndView.addObject("error", error);

        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile , Errors errors,HttpSession httpSession){
        if(errors.hasErrors()){
            log.error("Profile Page form validation has been failed!");
            return "profile.html";
        }
        Person person = (Person) httpSession.getAttribute("loggedInUser");
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if(person.getAddress() == null || !(person.getAddress().getAddressId()>0))
        {
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(Integer.parseInt(profile.getZipCode()));

        personRepository.save(person);
        httpSession.setAttribute("loggedInUser",person);
        log.info("profile has been updated successfully");
            return "redirect:/profile?updated="+person.getPersonId();
    }

}

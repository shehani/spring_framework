package com.example.controller;

import com.example.model.Church;
import com.example.model.Person;
import com.example.repository.AdminRepository;
import com.example.repository.PersonRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PersonRepository personRepository;
    @GetMapping(value = "/displayChurch")
    public ModelAndView displayChurch(Model model){
        ModelAndView modelAndView = new ModelAndView("church.html");
        List<Church> churchList = adminRepository.findAll();
        Church church = new Church();
        modelAndView.addObject("church",church);
        modelAndView.addObject("churchList",churchList);
        return modelAndView;
    }

    @PostMapping("/saveChurch")
    public String saveChurch(@Valid @ModelAttribute("church") Church church , Errors errors)
    {
        Church savedChurch =adminRepository.save(church);
        if(savedChurch!=null && savedChurch.getChurchId()>0){
            log.info("Church has been successfully saved");
        }else{
            log.error("Error getting while saving church: "+church.getName());
        }

        return "redirect:/admin/displayChurch";
    }

    @GetMapping("/displayUsers")
    public ModelAndView displayUsers(Model model,@RequestParam(value = "id") int id){
        ModelAndView modelAndView = new ModelAndView("user.html");
        Optional<Church> church = adminRepository.findById(id);
        modelAndView.addObject("churchOb" , church.get());
        modelAndView.addObject("church_name" , church.get().getName());
        return modelAndView;
    }

    @GetMapping("/removeChurch")
    public String removeChurch(@RequestParam(value = "id") int id){
        Optional<Church> church = adminRepository.findById(id);
        List<Person> personList = church.get().getPersons();
        for(Person person : personList){
            person.setChurchOb(null);
            personRepository .save(person);
        }
        adminRepository.deleteById(id);
        return "redirect:/admin/displayChurch";

    }
}

package com.example.controller;

import com.example.model.Church;
import com.example.model.Grade;
import com.example.model.Person;
import com.example.model.Student;
import com.example.repository.AdminRepository;
import com.example.repository.GradeRepository;
import com.example.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    GradeRepository gradeRepository;

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

    @GetMapping("/displayGrades")
    public ModelAndView displayGrade(Model model){
        List<Grade> grades = gradeRepository.findByOrderByNameDesc();
        ModelAndView modelAndView = new ModelAndView("grade.html");
        modelAndView.addObject("gradeList", grades);
        modelAndView.addObject("grade", new Grade());

        return modelAndView;
    }

    @PostMapping("/saveGrades")
    public String saveGrades(Model model, @Valid @ModelAttribute("grade") Grade grade , Errors error){
        if(error.hasErrors()){
            log.info("Idea form validation failed due to "+error.toString());
            return "grade.html";
        }
        Grade savedGrade = gradeRepository.save(grade);
        if(grade != null && grade.getGradeId()>0){
            log.info("Grade has been successfully saved");
            return "redirect:/admin/displayGrades?success=true";
        }else {
            log.info("Failed Grade Saving");
            return "redirect:/admin/displayGrades?success=false";
        }

    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudentForEachGrade(Model model, @RequestParam("id") int id, HttpSession httpSession){
        Optional<Grade> grade = gradeRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("student.html");
        modelAndView.addObject("student",new Student());
        modelAndView.addObject("grade",grade.get());
        httpSession.setAttribute("grades", grade.get());
        return modelAndView;
    }

    @PostMapping("enrollStudent")
    public ModelAndView enrollStudent(HttpSession httpSession,@Valid @ModelAttribute("student") Student student , Errors error){
        ModelAndView modelAndView = new ModelAndView();
        if(error.hasErrors()){
            modelAndView.setViewName("student.html");
            return modelAndView;
        }

        Grade grade = (Grade) httpSession.getAttribute("grades");
        Person person = personRepository.findByEmail(student.getEmail());
        if(person==null && !(person.getPersonId()>0)){
            log.info("Invalid email has been entered");
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+grade.getGradeId()+"&error=true");
            return modelAndView;
        }
        Set<Grade> gradeSet = person.getGrades();
        gradeSet.add(grade);
        grade.getPersons().add(person);
        personRepository.save(person);
        httpSession.setAttribute("grades",grade);
        modelAndView.setViewName("redirect:/admin/displayStudents?id="+grade.getGradeId());
        return modelAndView;
    }

    @GetMapping("/removeEnrollment")
    public String removeEnrollment(@RequestParam int id,HttpSession httpSession){

        Grade grade = (Grade) httpSession.getAttribute("grades");
        Optional<Person> person = personRepository.findById(id);
        person.get().getGrades().remove(grade);
        grade.getPersons().remove(person);
        personRepository.save(person.get());
        httpSession.setAttribute("grades",grade);
        return "redirect:/admin/displayStudents?id="+grade.getGradeId();


    }
}

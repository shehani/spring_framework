package com.example.controller;

import com.example.model.Idea;
import com.example.service.IdeaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class IdeaController {

    private IdeaService ideaService;

    /**
     * Initializing ideaService Feild
     * @param ideaService - Constructor level Autowired bean
     */
    @Autowired
    public IdeaController(IdeaService ideaService){
        this.ideaService = ideaService;
    }

    @RequestMapping(path="/idea")
    public String displayIdeaFormPage(Model model) throws Exception{
        model.addAttribute("tellUs",new Idea());
        //throw new Exception("Error Code - Idea01");
        return "IdeaForm.html";
    }



    @RequestMapping(path="/postIdea",method = POST)
    public String saveIdea(@Valid @ModelAttribute("tellUs") Idea idea, Errors error){
        if(error.hasErrors()){
            log.info("Idea form validation failed due to "+error.toString());
            return "IdeaForm.html";
        }
        boolean savedStatus=ideaService.isSaved(idea);
        log.info("Successfully Saved "+savedStatus);
        return "redirect:/idea";

    }

    @RequestMapping("/IdeaList")
    public ModelAndView displayIdea(Model model){
        ModelAndView modelAndView = new ModelAndView("IdeaList.html");
        List<Idea> ideaList = ideaService.getAllIdea();
        modelAndView.addObject("ideaList", ideaList);
        return modelAndView;
    }

    @RequestMapping("/IdeaList/{name}")
    public ModelAndView displayIdea(Model model, @PathVariable String name){
        ModelAndView modelAndView = new ModelAndView("IdeaList.html");
        List<Idea> ideaList = ideaService.getIdeaByName(name);
        modelAndView.addObject("ideaList", ideaList);
        return modelAndView;
    }

    @RequestMapping("/CloseIdea")
    public String closeIdea(@RequestParam int id, Authentication authentication){
        boolean isUpdated =false;
        isUpdated= ideaService.closeIdea(id,authentication.getName());
        log.info("Record has been successfully closed  "+ isUpdated);
        return "redirect:/IdeaList";
    }
}

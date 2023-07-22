package com.example.globalExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class is for handling exception globally
 * to display proper message to user
 * via a nice UI
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandlingControllerAdvise {

    @ExceptionHandler(Exception.class)
    public ModelAndView displayErrorPage(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errormsg", exception.getMessage());
        log.error(exception.toString());
        return modelAndView;
    }

}

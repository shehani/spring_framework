package com.example.controller;

import com.example.model.Schedule;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @RequestMapping(path = "/schedule/{requiredSchedule}")
    public String displaySchedule(@PathVariable (required = false) String requiredSchedule, Model model){
       scheduleService.setScheduleArray(requiredSchedule,model);
       return "Mass-Schedule.html";
    }


}

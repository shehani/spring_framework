package com.example.service;

import com.example.model.Schedule;
import com.example.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    public void setScheduleArray(String requiredSchedule,Model model){
        List<Schedule> arrayList = null;
        Iterable<Schedule> scheduleIterator = scheduleRepository.findAll();
        arrayList = StreamSupport.stream(scheduleIterator.spliterator(),false).collect(Collectors.toList());
        Schedule.Type[] arrType = Schedule.Type.values();
        for(Schedule.Type type : arrType){
            model.addAttribute(type.toString(), (arrayList.stream().filter(schedule -> schedule.getType().equals(type))).collect(Collectors.toList()));
        }
        model.addAttribute("requiredSchedule",requiredSchedule);


    }
}

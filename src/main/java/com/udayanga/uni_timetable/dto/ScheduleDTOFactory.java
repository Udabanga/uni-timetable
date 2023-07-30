package com.udayanga.uni_timetable.dto;

import com.udayanga.uni_timetable.model.*;
import com.udayanga.uni_timetable.model.Module;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class ScheduleDTOFactory {
    public ScheduleDTO createScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setStarTime(schedule.getStarTime());
        scheduleDTO.setEndTime(schedule.getEndTime());
        scheduleDTO.setClassroom(schedule.getClassroom());
        scheduleDTO.setModule(schedule.getModule());
        scheduleDTO.setUser(schedule.getUser());
        scheduleDTO.setBatches(schedule.getBatches());

        return scheduleDTO;

    }

    public Schedule createSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setStarTime(scheduleDTO.getStarTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule.setClassroom(scheduleDTO.getClassroom());
        schedule.setModule(scheduleDTO.getModule());
        schedule.setUser(scheduleDTO.getUser());
        schedule.setBatches(scheduleDTO.getBatches());
        return schedule;
    }
}

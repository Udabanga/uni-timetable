package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.command.schedule.ScheduleCommand;
import com.udayanga.uni_timetable.dto.ScheduleDTO;
import com.udayanga.uni_timetable.model.Schedule;
import com.udayanga.uni_timetable.repository.ScheduleRepository;

public class CreateScheduleCommand implements ScheduleCommand {
    public final ScheduleRepository scheduleRepository;
    public final ScheduleDTO scheduleDTO;

    public CreateScheduleCommand(ScheduleRepository scheduleRepository, ScheduleDTO scheduleDTO){
        this.scheduleRepository = scheduleRepository;
        this.scheduleDTO = scheduleDTO;
    }
    @Override
    public Schedule execute() {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setStarTime(scheduleDTO.getStarTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule.setClassroom(scheduleDTO.getClassroom());
        schedule.setModule(scheduleDTO.getModule());
        schedule.setUser(scheduleDTO.getUser());
        schedule.setBatches(scheduleDTO.getBatches());
        return scheduleRepository.save(schedule);
    }
}

package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.command.schedule.ScheduleCommand;
import com.udayanga.uni_timetable.dto.ScheduleDTO;
import com.udayanga.uni_timetable.model.Schedule;
import com.udayanga.uni_timetable.repository.ScheduleRepository;

public class UpdateScheduleCommand implements ScheduleCommand {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleDTO scheduleDTO;

    public UpdateScheduleCommand(ScheduleRepository scheduleRepository, ScheduleDTO scheduleDTO) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleDTO = scheduleDTO;
    }

    @Override
    public Schedule execute() {
        Schedule schedule = scheduleRepository.findById(scheduleDTO.getId()).orElseThrow();
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

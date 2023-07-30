package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.model.Schedule;
import com.udayanga.uni_timetable.repository.ScheduleRepository;

import java.util.List;

public class FindAllSchedulesCommand {
    private final ScheduleRepository scheduleRepository;

    public FindAllSchedulesCommand(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> execute() {
        return scheduleRepository.findAll();
    }
}

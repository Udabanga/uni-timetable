package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.command.schedule.ScheduleCommand;
import com.udayanga.uni_timetable.model.Schedule;
import com.udayanga.uni_timetable.repository.ScheduleRepository;

public class FindScheduleCommand implements ScheduleCommand {
    private final ScheduleRepository scheduleRepository;
    private final Long id;

    public FindScheduleCommand(ScheduleRepository scheduleRepository, Long id) {
        this.scheduleRepository = scheduleRepository;
        this.id = id;
    }

    @Override
    public Schedule execute() {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        return schedule;
    }
}

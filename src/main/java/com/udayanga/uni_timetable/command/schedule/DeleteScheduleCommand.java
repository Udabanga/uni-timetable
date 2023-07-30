package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.command.schedule.ScheduleCommand;
import com.udayanga.uni_timetable.model.Schedule;
import com.udayanga.uni_timetable.repository.ScheduleRepository;

public class DeleteScheduleCommand implements ScheduleCommand {
    private final ScheduleRepository scheduleRepository;
    private final Long id;

    public DeleteScheduleCommand(ScheduleRepository scheduleRepository, Long id) {
        this.scheduleRepository = scheduleRepository;
        this.id = id;
    }

    @Override
    public Schedule execute() {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        scheduleRepository.delete(schedule);
        return schedule;
    }
}

package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.model.Schedule;
import com.udayanga.uni_timetable.repository.ScheduleRepository;

import java.util.List;

public class FindAllSchedulesByClassroomCommand {
    private final ScheduleRepository scheduleRepository;
    private final Long id;

    public FindAllSchedulesByClassroomCommand(ScheduleRepository scheduleRepository, Long id) {
        this.scheduleRepository = scheduleRepository;
        this.id = id;
    }

    public List<Schedule> execute() {
        return scheduleRepository.findAllByClassroomId(id);
    }
}

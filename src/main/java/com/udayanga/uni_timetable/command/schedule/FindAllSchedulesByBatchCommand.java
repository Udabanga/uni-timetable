package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.model.Schedule;
import com.udayanga.uni_timetable.repository.ScheduleRepository;

import java.util.List;

public class FindAllSchedulesByBatchCommand {
    private final ScheduleRepository scheduleRepository;
    private final Long id;

    public FindAllSchedulesByBatchCommand(ScheduleRepository scheduleRepository, Long id) {
        this.scheduleRepository = scheduleRepository;
        this.id = id;
    }

    public List<Schedule> execute() {
        return scheduleRepository.findAllByBatchesId(id);
    }
}

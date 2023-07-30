package com.udayanga.uni_timetable.command.schedule;

import com.udayanga.uni_timetable.command.schedule.*;
import com.udayanga.uni_timetable.dto.ScheduleDTO;
import com.udayanga.uni_timetable.repository.ScheduleRepository;
import org.springframework.stereotype.Component;

@Component
public class ScheduleCommandFactory {
    private final ScheduleRepository scheduleRepository;

    public ScheduleCommandFactory(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleCommand createSaveCommand(ScheduleDTO scheduleDTO){
        return new CreateScheduleCommand(scheduleRepository, scheduleDTO);
    }

    public ScheduleCommand createUpdateCommand(ScheduleDTO scheduleDTO){
        return new UpdateScheduleCommand(scheduleRepository, scheduleDTO);
    }

    public ScheduleCommand createDeleteCommand(Long id){
        return new DeleteScheduleCommand(scheduleRepository, id);
    }

    public ScheduleCommand createFindCommand(Long id){
        return new FindScheduleCommand(scheduleRepository, id);
    }

    public FindAllSchedulesCommand createFindAllCommand() {
        return new FindAllSchedulesCommand(scheduleRepository);
    }

    public FindAllSchedulesByLecturerCommand createFindAllByLecturerCommand(Long id) {
        return new FindAllSchedulesByLecturerCommand(scheduleRepository, id);
    }

    public FindAllSchedulesByClassroomCommand createFindAllByClassroomCommand(Long id) {
        return new FindAllSchedulesByClassroomCommand(scheduleRepository, id);
    }

    public FindAllSchedulesByBatchCommand createFindAllByBatchCommand(Long id) {
        return new FindAllSchedulesByBatchCommand(scheduleRepository, id);
    }
}

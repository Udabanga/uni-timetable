package com.udayanga.uni_timetable.service;

import com.udayanga.uni_timetable.command.schedule.ScheduleCommandFactory;
import com.udayanga.uni_timetable.dto.ScheduleDTO;
import com.udayanga.uni_timetable.dto.ScheduleDTOFactory;
import com.udayanga.uni_timetable.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleCommandFactory scheduleCommandFactory;
    private final ScheduleDTOFactory scheduleDTOFactory;

    public ScheduleService(ScheduleCommandFactory scheduleCommandFactory, ScheduleDTOFactory scheduleDTOFactory) {
        this.scheduleCommandFactory = scheduleCommandFactory;
        this.scheduleDTOFactory = scheduleDTOFactory;
    }

    public ScheduleDTO save(ScheduleDTO scheduleDTO){
        Schedule schedule = scheduleCommandFactory.createSaveCommand(scheduleDTO).execute();
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }

    public ScheduleDTO update(ScheduleDTO scheduleDTO){
        Schedule schedule = scheduleCommandFactory.createUpdateCommand(scheduleDTO).execute();
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }

    public void delete(Long id){
        scheduleCommandFactory.createDeleteCommand(id).execute();
    }

    public ScheduleDTO find(Long id){
        Schedule schedule = scheduleCommandFactory.createFindCommand(id).execute();
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


    public List<ScheduleDTO> findAll() {
        List<Schedule> schedules = (List<Schedule>) scheduleCommandFactory.createFindAllCommand().execute();
        return schedules.stream()
                .map(scheduleDTOFactory::createScheduleDTO)
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> findAllByLecturer(Long id) {
        List<Schedule> schedules = (List<Schedule>) scheduleCommandFactory.createFindAllByLecturerCommand(id).execute();
        return schedules.stream()
                .map(scheduleDTOFactory::createScheduleDTO)
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> findAllByClassroom(Long id) {
        List<Schedule> schedules = (List<Schedule>) scheduleCommandFactory.createFindAllByClassroomCommand(id).execute();
        return schedules.stream()
                .map(scheduleDTOFactory::createScheduleDTO)
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> findAllByBatch(Long id) {
        List<Schedule> schedules = (List<Schedule>) scheduleCommandFactory.createFindAllByBatchCommand(id).execute();
        return schedules.stream()
                .map(scheduleDTOFactory::createScheduleDTO)
                .collect(Collectors.toList());
    }

}

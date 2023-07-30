package com.udayanga.uni_timetable.command.classroom;

import com.udayanga.uni_timetable.command.classroom.ClassroomCommand;
import com.udayanga.uni_timetable.dto.ClassroomDTO;
import com.udayanga.uni_timetable.model.Classroom;
import com.udayanga.uni_timetable.repository.ClassroomRepository;

public class UpdateClassroomCommand implements ClassroomCommand {
    private final ClassroomRepository classroomRepository;
    private final ClassroomDTO classroomDTO;

    public UpdateClassroomCommand(ClassroomRepository classroomRepository, ClassroomDTO classroomDTO) {
        this.classroomRepository = classroomRepository;
        this.classroomDTO = classroomDTO;
    }

    @Override
    public Classroom execute() {
        Classroom classroom = classroomRepository.findById(classroomDTO.getId()).orElseThrow();
        classroom.setBuilding(classroomDTO.getBuilding());
        classroom.setFloor(classroomDTO.getFloor());
        classroom.setRoom_number(classroomDTO.getRoom_number());
        classroom.setType(classroomDTO.getType());
        return classroomRepository.save(classroom);
    }
}

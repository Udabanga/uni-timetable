package com.udayanga.uni_timetable.command.classroom;

import com.udayanga.uni_timetable.command.classroom.ClassroomCommand;
import com.udayanga.uni_timetable.dto.ClassroomDTO;
import com.udayanga.uni_timetable.model.Classroom;
import com.udayanga.uni_timetable.repository.ClassroomRepository;

public class CreateClassroomCommand implements ClassroomCommand {
    public final ClassroomRepository classroomRepository;
    public final ClassroomDTO classroomDTO;

    public CreateClassroomCommand(ClassroomRepository classroomRepository, ClassroomDTO classroomDTO){
        this.classroomRepository = classroomRepository;
        this.classroomDTO = classroomDTO;
    }
    @Override
    public Classroom execute() {
        Classroom classroom = new Classroom();
        classroom.setBuilding(classroomDTO.getBuilding());
        classroom.setFloor(classroomDTO.getFloor());
        classroom.setRoom_number(classroomDTO.getRoom_number());
        classroom.setType(classroomDTO.getType());
        return classroomRepository.save(classroom);
    }
}

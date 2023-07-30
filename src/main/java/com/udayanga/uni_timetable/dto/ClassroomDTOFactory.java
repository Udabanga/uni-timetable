package com.udayanga.uni_timetable.dto;

import com.udayanga.uni_timetable.model.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomDTOFactory {
    public ClassroomDTO createClassroomDTO(Classroom classroom){
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setId(classroom.getId());
        classroomDTO.setBuilding(classroom.getBuilding());
        classroomDTO.setFloor(classroom.getFloor());
        classroomDTO.setRoom_number(classroom.getRoom_number());
        classroomDTO.setType(classroom.getType());
        return classroomDTO;

    }

    public Classroom createClassroom(ClassroomDTO classroomDTO){
        Classroom classroom = new Classroom();
        classroom.setId(classroomDTO.getId());
        classroom.setBuilding(classroomDTO.getBuilding());
        classroom.setFloor(classroomDTO.getFloor());
        classroom.setRoom_number(classroomDTO.getRoom_number());
        classroom.setType(classroomDTO.getType());
        return classroom;
    }
}

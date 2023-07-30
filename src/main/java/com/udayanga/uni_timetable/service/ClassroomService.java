package com.udayanga.uni_timetable.service;

import com.udayanga.uni_timetable.command.classroom.ClassroomCommandFactory;
import com.udayanga.uni_timetable.dto.ClassroomDTO;
import com.udayanga.uni_timetable.dto.ClassroomDTOFactory;
import com.udayanga.uni_timetable.model.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomService {
    private final ClassroomCommandFactory classroomCommandFactory;
    private final ClassroomDTOFactory classroomDTOFactory;

    public ClassroomService(ClassroomCommandFactory classroomCommandFactory, ClassroomDTOFactory classroomDTOFactory) {
        this.classroomCommandFactory = classroomCommandFactory;
        this.classroomDTOFactory = classroomDTOFactory;
    }

    public ClassroomDTO save(ClassroomDTO classroomDTO){
        Classroom classroom = classroomCommandFactory.createSaveCommand(classroomDTO).execute();
        classroomDTO.setId(classroom.getId());
        return classroomDTO;
    }

    public ClassroomDTO update(ClassroomDTO classroomDTO){
        Classroom classroom = classroomCommandFactory.createUpdateCommand(classroomDTO).execute();
        classroomDTO.setId(classroom.getId());
        return classroomDTO;
    }

    public void delete(Long id){
        classroomCommandFactory.createDeleteCommand(id).execute();
    }

    public ClassroomDTO find(Long id){
        Classroom classroom = classroomCommandFactory.createFindCommand(id).execute();
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setId(classroom.getId());
        classroomDTO.setBuilding(classroom.getBuilding());
        classroomDTO.setFloor(classroom.getFloor());
        classroomDTO.setRoom_number(classroom.getRoom_number());
        classroomDTO.setType(classroom.getType());
        return classroomDTO;
    }


    public List<ClassroomDTO> findAll() {
        List<Classroom> classrooms = (List<Classroom>) classroomCommandFactory.createFindAllCommand().execute();
        return classrooms.stream()
                .map(classroomDTOFactory::createClassroomDTO)
                .collect(Collectors.toList());
    }

}

package com.udayanga.uni_timetable.command.classroom;

import com.udayanga.uni_timetable.command.classroom.*;
import com.udayanga.uni_timetable.dto.ClassroomDTO;
import com.udayanga.uni_timetable.repository.ClassroomRepository;
import org.springframework.stereotype.Component;

@Component
public class ClassroomCommandFactory {
    private final ClassroomRepository classroomRepository;

    public ClassroomCommandFactory(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public ClassroomCommand createSaveCommand(ClassroomDTO classroomDTO){
        return new CreateClassroomCommand(classroomRepository, classroomDTO);
    }

    public ClassroomCommand createUpdateCommand(ClassroomDTO classroomDTO){
        return new UpdateClassroomCommand(classroomRepository, classroomDTO);
    }

    public ClassroomCommand createDeleteCommand(Long id){
        return new DeleteClassroomCommand(classroomRepository, id);
    }

    public ClassroomCommand createFindCommand(Long id){
        return new FindClassroomCommand(classroomRepository, id);
    }

    public FindAllClassroomsCommand createFindAllCommand() {
        return new FindAllClassroomsCommand(classroomRepository);
    }
}

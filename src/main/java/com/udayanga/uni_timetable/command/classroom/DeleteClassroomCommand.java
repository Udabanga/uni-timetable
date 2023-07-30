package com.udayanga.uni_timetable.command.classroom;

import com.udayanga.uni_timetable.command.classroom.ClassroomCommand;
import com.udayanga.uni_timetable.model.Classroom;
import com.udayanga.uni_timetable.repository.ClassroomRepository;

public class DeleteClassroomCommand implements ClassroomCommand {
    private final ClassroomRepository classroomRepository;
    private final Long id;

    public DeleteClassroomCommand(ClassroomRepository classroomRepository, Long id) {
        this.classroomRepository = classroomRepository;
        this.id = id;
    }

    @Override
    public Classroom execute() {
        Classroom classroom = classroomRepository.findById(id).orElseThrow();
        classroomRepository.delete(classroom);
        return classroom;
    }
}

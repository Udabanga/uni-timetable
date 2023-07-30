package com.udayanga.uni_timetable.command.classroom;

import com.udayanga.uni_timetable.model.Classroom;
import com.udayanga.uni_timetable.repository.ClassroomRepository;

import java.util.List;

public class FindAllClassroomsCommand {
    private final ClassroomRepository classroomRepository;

    public FindAllClassroomsCommand(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> execute() {
        return classroomRepository.findAll();
    }
}

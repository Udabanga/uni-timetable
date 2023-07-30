package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.dto.ClassroomDTO;
import com.udayanga.uni_timetable.service.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }


    @PostMapping
    public ResponseEntity<ClassroomDTO> save(@RequestBody ClassroomDTO classroomDTO) {
        ClassroomDTO savedClassroom = classroomService.save(classroomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClassroom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> update(@PathVariable Long id, @RequestBody ClassroomDTO classroomDTO) {
        classroomDTO.setId(id);
        ClassroomDTO updatedClassroom = classroomService.update(classroomDTO);
        return ResponseEntity.ok(updatedClassroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> find(@PathVariable Long id) {
        ClassroomDTO classroom = classroomService.find(id);
        return ResponseEntity.ok(classroom);
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDTO>> findAll() {
        List<ClassroomDTO> classrooms = classroomService.findAll();
        return ResponseEntity.ok(classrooms);
    }
}

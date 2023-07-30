package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.dto.ScheduleDTO;
import com.udayanga.uni_timetable.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping
    public ResponseEntity<ScheduleDTO> save(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO savedSchedule = scheduleService.save(scheduleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> update(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        scheduleDTO.setId(id);
        ScheduleDTO updatedSchedule = scheduleService.update(scheduleDTO);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> find(@PathVariable Long id) {
        ScheduleDTO schedule = scheduleService.find(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> findAll() {
        List<ScheduleDTO> schedules = scheduleService.findAll();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/lecturer/{id}")
    public ResponseEntity<List<ScheduleDTO>> findAllByLecturer(@PathVariable Long id) {
        List<ScheduleDTO> schedules = scheduleService.findAllByLecturer(id);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/classroom/{id}")
    public ResponseEntity<List<ScheduleDTO>> findAllByClassroom(@PathVariable Long id) {
        List<ScheduleDTO> schedules = scheduleService.findAllByClassroom(id);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/batch/{id}")
    public ResponseEntity<List<ScheduleDTO>> findAllByBatch(@PathVariable Long id) {
        List<ScheduleDTO> schedules = scheduleService.findAllByBatch(id);
        return ResponseEntity.ok(schedules);
    }
}

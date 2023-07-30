package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.dto.ModuleDTO;
import com.udayanga.uni_timetable.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }


    @PostMapping
    public ResponseEntity<ModuleDTO> save(@RequestBody ModuleDTO moduleDTO) {
        ModuleDTO savedModule = moduleService.save(moduleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedModule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleDTO> update(@PathVariable Long id, @RequestBody ModuleDTO moduleDTO) {
        moduleDTO.setId(id);
        ModuleDTO updatedModule = moduleService.update(moduleDTO);
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleDTO> find(@PathVariable Long id) {
        ModuleDTO module = moduleService.find(id);
        return ResponseEntity.ok(module);
    }

    @GetMapping
    public ResponseEntity<List<ModuleDTO>> findAll() {
        List<ModuleDTO> modules = moduleService.findAll();
        return ResponseEntity.ok(modules);
    }
}

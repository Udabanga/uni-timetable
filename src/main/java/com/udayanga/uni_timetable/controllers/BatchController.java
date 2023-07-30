package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.dto.BatchDTO;
import com.udayanga.uni_timetable.service.BatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
public class BatchController {
    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }


    @PostMapping
    public ResponseEntity<BatchDTO> save(@RequestBody BatchDTO batchDTO) {
        BatchDTO savedBatch = batchService.save(batchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BatchDTO> update(@PathVariable Long id, @RequestBody BatchDTO batchDTO) {
        batchDTO.setId(id);
        BatchDTO updatedBatch = batchService.update(batchDTO);
        return ResponseEntity.ok(updatedBatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        batchService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchDTO> find(@PathVariable Long id) {
        BatchDTO batch = batchService.find(id);
        return ResponseEntity.ok(batch);
    }

    @GetMapping
    public ResponseEntity<List<BatchDTO>> findAll() {
        List<BatchDTO> batches = batchService.findAll();
        return ResponseEntity.ok(batches);
    }
}

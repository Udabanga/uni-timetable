package com.udayanga.uni_timetable.command.batch;

import com.udayanga.uni_timetable.model.Batch;
import com.udayanga.uni_timetable.repository.BatchRepository;

import java.util.List;

public class FindAllBatchesCommand {
    private final BatchRepository batchRepository;

    public FindAllBatchesCommand(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public List<Batch> execute() {
        return batchRepository.findAll();
    }
}

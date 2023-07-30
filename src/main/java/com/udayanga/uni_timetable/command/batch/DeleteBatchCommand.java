package com.udayanga.uni_timetable.command.batch;

import com.udayanga.uni_timetable.command.batch.BatchCommand;
import com.udayanga.uni_timetable.model.Batch;
import com.udayanga.uni_timetable.repository.BatchRepository;

public class DeleteBatchCommand implements BatchCommand {
    private final BatchRepository batchRepository;
    private final Long id;

    public DeleteBatchCommand(BatchRepository batchRepository, Long id) {
        this.batchRepository = batchRepository;
        this.id = id;
    }

    @Override
    public Batch execute() {
        Batch batch = batchRepository.findById(id).orElseThrow();
        batchRepository.delete(batch);
        return batch;
    }
}

package com.udayanga.uni_timetable.command.batch;

import com.udayanga.uni_timetable.command.batch.BatchCommand;
import com.udayanga.uni_timetable.dto.BatchDTO;
import com.udayanga.uni_timetable.model.Batch;
import com.udayanga.uni_timetable.repository.BatchRepository;

public class CreateBatchCommand implements BatchCommand {
    public final BatchRepository batchRepository;
    public final BatchDTO batchDTO;

    public CreateBatchCommand(BatchRepository batchRepository, BatchDTO batchDTO){
        this.batchRepository = batchRepository;
        this.batchDTO = batchDTO;
    }
    @Override
    public Batch execute() {
        Batch batch = new Batch();
        batch.setBatchCode(batchDTO.getBatchCode());
        batch.setFaculty(batchDTO.getFaculty());
        batch.setSemester(batchDTO.getSemester());
        batch.setYear(batchDTO.getYear());
        return batchRepository.save(batch);
    }
}

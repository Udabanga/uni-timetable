package com.udayanga.uni_timetable.dto;

import com.udayanga.uni_timetable.model.Batch;
import org.springframework.stereotype.Component;

@Component
public class BatchDTOFactory {
    public BatchDTO createBatchDTO(Batch batch){
        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setId(batch.getId());
        batchDTO.setBatchCode(batch.getBatchCode());
        batchDTO.setFaculty(batch.getFaculty());
        batchDTO.setSemester(batch.getSemester());
        batchDTO.setYear(batch.getYear());
        return batchDTO;

    }

    public Batch createBatch(BatchDTO batchDTO){
        Batch batch = new Batch();
        batch.setId(batchDTO.getId());
        batch.setBatchCode(batchDTO.getBatchCode());
        batch.setFaculty(batchDTO.getFaculty());
        batch.setSemester(batchDTO.getSemester());
        batch.setYear(batchDTO.getYear());
        return batch;
    }
}

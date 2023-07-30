package com.udayanga.uni_timetable.service;

import com.udayanga.uni_timetable.command.batch.BatchCommandFactory;
import com.udayanga.uni_timetable.dto.BatchDTO;
import com.udayanga.uni_timetable.dto.BatchDTOFactory;
import com.udayanga.uni_timetable.model.Batch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService {
    private final BatchCommandFactory batchCommandFactory;
    private final BatchDTOFactory batchDTOFactory;

    public BatchService(BatchCommandFactory batchCommandFactory, BatchDTOFactory batchDTOFactory) {
        this.batchCommandFactory = batchCommandFactory;
        this.batchDTOFactory = batchDTOFactory;
    }

    public BatchDTO save(BatchDTO batchDTO){
        Batch batch = batchCommandFactory.createSaveCommand(batchDTO).execute();
        batchDTO.setId(batch.getId());
        return batchDTO;
    }

    public BatchDTO update(BatchDTO batchDTO){
        Batch batch = batchCommandFactory.createUpdateCommand(batchDTO).execute();
        batchDTO.setId(batch.getId());
        return batchDTO;
    }

    public void delete(Long id){
        batchCommandFactory.createDeleteCommand(id).execute();
    }

    public BatchDTO find(Long id){
        Batch batch = batchCommandFactory.createFindCommand(id).execute();
        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setId(batch.getId());
        batchDTO.setId(batch.getId());
        batchDTO.setBatchCode(batch.getBatchCode());
        batchDTO.setFaculty(batch.getFaculty());
        batchDTO.setSemester(batch.getSemester());
        batchDTO.setYear(batch.getYear());
        return batchDTO;
    }


    public List<BatchDTO> findAll() {
        List<Batch> batchs = (List<Batch>) batchCommandFactory.createFindAllCommand().execute();
        return batchs.stream()
                .map(batchDTOFactory::createBatchDTO)
                .collect(Collectors.toList());
    }

}

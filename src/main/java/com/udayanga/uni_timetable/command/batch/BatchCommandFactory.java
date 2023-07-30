package com.udayanga.uni_timetable.command.batch;

import com.udayanga.uni_timetable.command.batch.*;
import com.udayanga.uni_timetable.dto.BatchDTO;
import com.udayanga.uni_timetable.repository.BatchRepository;
import org.springframework.stereotype.Component;

@Component
public class BatchCommandFactory {
    private final BatchRepository batchRepository;

    public BatchCommandFactory(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public BatchCommand createSaveCommand(BatchDTO batchDTO){
        return new CreateBatchCommand(batchRepository, batchDTO);
    }

    public BatchCommand createUpdateCommand(BatchDTO batchDTO){
        return new UpdateBatchCommand(batchRepository, batchDTO);
    }

    public BatchCommand createDeleteCommand(Long id){
        return new DeleteBatchCommand(batchRepository, id);
    }

    public BatchCommand createFindCommand(Long id){
        return new FindBatchCommand(batchRepository, id);
    }

    public FindAllBatchesCommand createFindAllCommand() {
        return new FindAllBatchesCommand(batchRepository);
    }
}

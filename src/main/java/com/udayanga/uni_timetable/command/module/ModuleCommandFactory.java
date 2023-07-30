package com.udayanga.uni_timetable.command.module;

import com.udayanga.uni_timetable.dto.ModuleDTO;
import com.udayanga.uni_timetable.repository.ModuleRepository;
import org.springframework.stereotype.Component;

@Component
public class ModuleCommandFactory {
    private final ModuleRepository moduleRepository;

    public ModuleCommandFactory(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public ModuleCommand createSaveCommand(ModuleDTO moduleDTO){
        return new CreateModuleCommand(moduleRepository, moduleDTO);
    }

    public ModuleCommand createUpdateCommand(ModuleDTO moduleDTO){
        return new UpdateModuleCommand(moduleRepository, moduleDTO);
    }

    public ModuleCommand createDeleteCommand(Long id){
        return new DeleteModuleCommand(moduleRepository, id);
    }

    public ModuleCommand createFindCommand(Long id){
        return new FindModuleCommand(moduleRepository, id);
    }

    public FindAllModulesCommand createFindAllCommand() {
        return new FindAllModulesCommand(moduleRepository);
    }
}

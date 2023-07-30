package com.udayanga.uni_timetable.command.module;

import com.udayanga.uni_timetable.dto.ModuleDTO;
import com.udayanga.uni_timetable.model.Module;
import com.udayanga.uni_timetable.repository.ModuleRepository;

public class CreateModuleCommand implements ModuleCommand {
    public final ModuleRepository moduleRepository;
    public final ModuleDTO moduleDTO;

    public CreateModuleCommand(ModuleRepository moduleRepository, ModuleDTO moduleDTO){
        this.moduleRepository = moduleRepository;
        this.moduleDTO = moduleDTO;
    }
    @Override
    public Module execute() {
        Module module = new Module();
        module.setModuleName(moduleDTO.getModuleName());
        return moduleRepository.save(module);
    }
}

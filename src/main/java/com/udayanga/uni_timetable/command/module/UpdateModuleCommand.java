package com.udayanga.uni_timetable.command.module;

import com.udayanga.uni_timetable.dto.ModuleDTO;
import com.udayanga.uni_timetable.model.Module;
import com.udayanga.uni_timetable.repository.ModuleRepository;

public class UpdateModuleCommand implements ModuleCommand{
    private final ModuleRepository moduleRepository;
    private final ModuleDTO moduleDTO;

    public UpdateModuleCommand(ModuleRepository moduleRepository, ModuleDTO moduleDTO) {
        this.moduleRepository = moduleRepository;
        this.moduleDTO = moduleDTO;
    }

    @Override
    public Module execute() {
        Module module = moduleRepository.findById(moduleDTO.getId()).orElseThrow();
        module.setModuleName(moduleDTO.getModuleName());
        return moduleRepository.save(module);
    }
}

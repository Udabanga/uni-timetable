package com.udayanga.uni_timetable.command.module;

import com.udayanga.uni_timetable.model.Module;
import com.udayanga.uni_timetable.repository.ModuleRepository;

public class FindModuleCommand implements ModuleCommand{
    private final ModuleRepository moduleRepository;
    private final Long id;

    public FindModuleCommand(ModuleRepository moduleRepository, Long id) {
        this.moduleRepository = moduleRepository;
        this.id = id;
    }

    @Override
    public Module execute() {
        Module module = moduleRepository.findById(id).orElseThrow();
        return module;
    }
}

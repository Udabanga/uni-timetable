package com.udayanga.uni_timetable.command.module;

import com.udayanga.uni_timetable.model.Module;
import com.udayanga.uni_timetable.repository.ModuleRepository;

import java.util.List;

public class FindAllModulesCommand{
    private final ModuleRepository moduleRepository;

    public FindAllModulesCommand(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> execute() {
        return moduleRepository.findAll();
    }
}

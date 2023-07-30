package com.udayanga.uni_timetable.service;

import com.udayanga.uni_timetable.command.module.ModuleCommandFactory;
import com.udayanga.uni_timetable.dto.ModuleDTO;
import com.udayanga.uni_timetable.dto.ModuleDTOFactory;
import com.udayanga.uni_timetable.model.Module;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleService {
    private final ModuleCommandFactory moduleCommandFactory;
    private final ModuleDTOFactory moduleDTOFactory;

    public ModuleService(ModuleCommandFactory moduleCommandFactory, ModuleDTOFactory moduleDTOFactory) {
        this.moduleCommandFactory = moduleCommandFactory;
        this.moduleDTOFactory = moduleDTOFactory;
    }

    public ModuleDTO save(ModuleDTO moduleDTO){
        Module module = moduleCommandFactory.createSaveCommand(moduleDTO).execute();
        moduleDTO.setId(module.getId());
        return moduleDTO;
    }

    public ModuleDTO update(ModuleDTO moduleDTO){
        Module module = moduleCommandFactory.createUpdateCommand(moduleDTO).execute();
        moduleDTO.setId(module.getId());
        return moduleDTO;
    }

    public void delete(Long id){
        moduleCommandFactory.createDeleteCommand(id).execute();
    }

    public ModuleDTO find(Long id){
        Module module = moduleCommandFactory.createFindCommand(id).execute();
        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setId(module.getId());
        moduleDTO.setModuleName(module.getModuleName());
        return moduleDTO;
    }


    public List<ModuleDTO> findAll() {
        List<Module> modules = (List<Module>) moduleCommandFactory.createFindAllCommand().execute();
        return modules.stream()
                .map(moduleDTOFactory::createModuleDTO)
                .collect(Collectors.toList());
    }

}

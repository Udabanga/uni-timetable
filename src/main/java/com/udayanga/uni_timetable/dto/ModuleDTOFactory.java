package com.udayanga.uni_timetable.dto;

import com.udayanga.uni_timetable.model.Module;
import org.springframework.stereotype.Component;

@Component
public class ModuleDTOFactory {
    public ModuleDTO createModuleDTO(Module module){
        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setId(module.getId());
        moduleDTO.setModuleName(module.getModuleName());
        return moduleDTO;

    }

    public Module createModule(ModuleDTO moduleDTO){
        Module module = new Module();
        module.setId(moduleDTO.getId());
        module.setModuleName(moduleDTO.getModuleName());
        return module;
    }
}

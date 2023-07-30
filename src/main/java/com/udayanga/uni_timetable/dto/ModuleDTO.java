package com.udayanga.uni_timetable.dto;

public class ModuleDTO {
    private Long id;
    private String moduleName;

    public ModuleDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}

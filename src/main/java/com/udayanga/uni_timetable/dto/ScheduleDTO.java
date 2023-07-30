package com.udayanga.uni_timetable.dto;

import com.udayanga.uni_timetable.model.Batch;
import com.udayanga.uni_timetable.model.Classroom;
import com.udayanga.uni_timetable.model.Module;
import com.udayanga.uni_timetable.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ScheduleDTO {
    private Long id;
    private Date date;
    private Date startTime;
    private Date endTime;
    private Classroom classroom;
    private Module module;
    private User user;
    private Set<Batch> batches = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStarTime() {
        return startTime;
    }

    public void setStarTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }
}

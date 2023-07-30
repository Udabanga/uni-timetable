package com.udayanga.uni_timetable.dto;

public class ClassroomDTO {
    private Long id;
    private String building;
    private int floor;
    private int roomNumber;
    private String type;

    public ClassroomDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoom_number() {
        return roomNumber;
    }

    public void setRoom_number(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

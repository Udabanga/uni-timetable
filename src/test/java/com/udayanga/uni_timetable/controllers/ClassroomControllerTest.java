package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.AbstractTest;
import com.udayanga.uni_timetable.model.Classroom;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClassroomControllerTest extends AbstractTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private static String createdClassroomId;
    private static HashMap<String, String> getClassroomResponse = new HashMap<String, String>();
    @Test
    @Order(1)
    void createClassroom() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/classrooms";
        Classroom classroom = new Classroom();
        classroom.setBuilding("TestBuilding");
        classroom.setFloor(1);
        classroom.setRoom_number(02);
        classroom.setType("testType");
        String inputJson = super.mapToJson(classroom);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        createdClassroomId = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");
        assertEquals(201, status);
    }

    @Test
    @Order(2)
    void getAllClassrooms() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/classrooms";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Classroom[] classroomlist = super.mapFromJson(content, Classroom[].class);
        assertTrue(classroomlist.length > 0);
    }

    @Test
    @Order(3)
    void getClassroomById() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/classrooms/"+createdClassroomId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();


        // Add keys and values (Country, City)
        getClassroomResponse.put("building", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("building"));
        getClassroomResponse.put("floor", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("floor"));
        getClassroomResponse.put("room_number", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("room_number"));
        getClassroomResponse.put("type", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("type"));
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    void updateClassroom() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/classrooms/"+createdClassroomId;
        Classroom classroom = new Classroom();
        classroom.setId(Long.valueOf(createdClassroomId));
        classroom.setBuilding("TESTUPDATE");
        classroom.setFloor(Integer.parseInt(getClassroomResponse.get("floor")));
        classroom.setRoom_number(Integer.parseInt(getClassroomResponse.get("room_number")));
        classroom.setType(getClassroomResponse.get("type"));
        String inputJson = super.mapToJson(classroom);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals("TESTUPDATE", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("building"));
    }

    @Test
    @Order(5)
    void deleteClassroom() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/classrooms/"+createdClassroomId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }
}
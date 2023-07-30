package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.AbstractTest;
import com.udayanga.uni_timetable.model.*;
import com.udayanga.uni_timetable.model.Module;
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

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScheduleControllerTest extends AbstractTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private static String createdScheduleId;
    private static HashMap<String, String> getScheduleResponse = new HashMap<String, String>();
    @Test
    @Order(1)
    void createSchedule() throws Exception {
        String inputJson;
        MvcResult mvcResult;
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Create test classroom
        Module module = new Module();
        module.setModuleName("TestBuilding");
        inputJson = super.mapToJson(module);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/modules")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        String createdModuleId = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");
        Module testModule = new Module();
        testModule.setId(Long.valueOf(createdModuleId));

        // Create test classroom
        Classroom classroom = new Classroom();
        classroom.setBuilding("TestBuilding");
        classroom.setFloor(1);
        classroom.setRoom_number(02);
        classroom.setType("testType");
        inputJson = super.mapToJson(classroom);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/classrooms")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        String createdClassroomId = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");
        Classroom testClassroom = new Classroom();
        testClassroom.setId(Long.valueOf(createdClassroomId));

        // Create test batch
        Batch batch = new Batch();
        batch.setBatchCode("TestBatchCode");
        batch.setFaculty("TestFaculty");
        batch.setSemester("1");
        batch.setYear("1");
        inputJson = super.mapToJson(batch);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/batches")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        String createdBatchId = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");
        Batch testBatch = new Batch();
        testBatch.setId(Long.valueOf(createdBatchId));


        String uri = "http://localhost:8080/schedules";
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Schedule schedule = new Schedule();
        schedule.setDate(df1.parse("2023-02-01"));
        schedule.setStarTime(df2.parse("2023-02-01T09:30"));
        schedule.setEndTime(df2.parse("2023-02-01T10:30"));

        User user = new User();
        user.setUsername("scheduleTestUser1");
        user.setEmail("scheduleTestUser1@test.com");
        user.setPassword("testtest");
        inputJson = super.mapToJson(user);

        mvcResult = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        User userLogin = new User();
        userLogin.setUsername("scheduleTestUser1");
        userLogin.setPassword("testtest");
        inputJson = super.mapToJson(userLogin);

        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        String createdUserId  = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");

        User testUser = new User();
        testUser.setId(Long.valueOf(createdUserId));

        schedule.setClassroom(testClassroom);
        schedule.setModule(testModule);
        schedule.setUser(testUser);
        schedule.setBatches(new HashSet<>(Arrays.asList(new Batch[]{testBatch})));
        inputJson = super.mapToJson(schedule);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        createdScheduleId = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");
        assertEquals(201, status);
    }

    @Test
    @Order(2)
    void getAllSchedules() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/schedules";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Schedule[] schedulelist = super.mapFromJson(content, Schedule[].class);
        assertTrue(schedulelist.length > 0);
    }

//    @Test
//    @Order(3)
//    void getScheduleById() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        String uri = "http://localhost:8080/schedules/"+createdScheduleId;
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//
//
//        // Add keys and values (Country, City)
//        getScheduleResponse.put("date", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("date"));
//        getScheduleResponse.put("startTime", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("starTime"));
//        getScheduleResponse.put("endTime", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("endTime"));
//        getScheduleResponse.put("classroom", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("classroom"));
//        getScheduleResponse.put("module", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("module"));
//        getScheduleResponse.put("user", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("user"));
//        getScheduleResponse.put("batches", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("batches"));
//        assertEquals(200, status);
//    }

//    @Test
//    @Order(4)
//    void updateSchedule() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        String uri = "http://localhost:8080/schedules/"+createdScheduleId;
//        Schedule schedule = new Schedule();
//        schedule.setId(Long.valueOf(createdScheduleId));
//        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//        schedule.setDate(df1.parse("2023-02-01"));
//        schedule.setStarTime(df2.parse(getScheduleResponse.get("startTime")));
//        schedule.setEndTime(df2.parse(getScheduleResponse.get("endTime")));
//        schedule.setClassroom(getScheduleResponse.get("classroom"));
//        schedule.setModule(getScheduleResponse.get("module"));
//        schedule.setUser(getScheduleResponse.get("user"));
//        schedule.setBatches(getScheduleResponse.get("batches"));
//        String inputJson = super.mapToJson(schedule);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals("2023-02-01T00:00:00.000+00:00", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("date"));
//    }

    @Test
    @Order(5)
    void deleteSchedule() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/schedules/"+createdScheduleId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }
}
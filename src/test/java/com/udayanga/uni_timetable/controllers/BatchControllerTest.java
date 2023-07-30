package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.AbstractTest;
import com.udayanga.uni_timetable.model.Batch;
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
class BatchControllerTest extends AbstractTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private static String createdBatchId;
    private static HashMap<String, String> getBatchResponse = new HashMap<String, String>();
    @Test
    @Order(1)
    void createBatch() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/batches";
        Batch batch = new Batch();
        batch.setBatchCode("TestBatchCode");
        batch.setFaculty("TestFaculty");
        batch.setSemester("1");
        batch.setYear("1");
        String inputJson = super.mapToJson(batch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        createdBatchId = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");
        assertEquals(201, status);
    }

    @Test
    @Order(2)
    void getAllBatchs() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/batches";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Batch[] batchlist = super.mapFromJson(content, Batch[].class);
        assertTrue(batchlist.length > 0);
    }

    @Test
    @Order(3)
    void getBatchById() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/batches/"+createdBatchId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();


        // Add keys and values (Country, City)
        getBatchResponse.put("batchCode", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("batchCode"));
        getBatchResponse.put("faculty", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("faculty"));
        getBatchResponse.put("semester", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("semester"));
        getBatchResponse.put("year", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("year"));
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    void updateBatch() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/batches/"+createdBatchId;
        Batch batch = new Batch();
        batch.setId(Long.valueOf(createdBatchId));
        batch.setBatchCode("TESTUPDATE");
        batch.setFaculty(getBatchResponse.get("faculty"));
        batch.setFaculty(getBatchResponse.get("semester"));
        batch.setFaculty(getBatchResponse.get("year"));
        String inputJson = super.mapToJson(batch);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("TESTUPDATE", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("batchCode"));
    }

    @Test
    @Order(5)
    void deleteBatch() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/batches/"+createdBatchId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }
}
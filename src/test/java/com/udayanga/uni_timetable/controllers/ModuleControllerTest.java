package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.AbstractTest;
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

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModuleControllerTest extends AbstractTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private static String createdModuleId;
    private static HashMap<String, String> getModuleResponse = new HashMap<String, String>();
    @Test
    @Order(1)
    void createModule() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/modules";
        Module module = new Module();
        module.setModuleName("TestModuleName");
        String inputJson = super.mapToJson(module);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        createdModuleId = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("id");
        assertEquals(201, status);
    }

    @Test
    @Order(2)
    void getAllModules() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/modules";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Module[] modulelist = super.mapFromJson(content, Module[].class);
        assertTrue(modulelist.length > 0);
    }

    @Test
    @Order(3)
    void getModuleById() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/modules/"+createdModuleId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();


        // Add keys and values (Country, City)
        getModuleResponse.put("moduleName", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("moduleName"));
        assertEquals(200, status);
    }

    @Test
    @Order(4)
    void updateModule() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/modules/"+createdModuleId;
        Module module = new Module();
        module.setId(Long.valueOf(createdModuleId));
        module.setModuleName("TESTUPDATE");
        String inputJson = super.mapToJson(module);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("TESTUPDATE", new JSONObject(mvcResult.getResponse().getContentAsString()).getString("moduleName"));
    }

    @Test
    @Order(5)
    void deleteModule() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/modules/"+createdModuleId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }
}
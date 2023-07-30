package com.udayanga.uni_timetable.controllers;

import com.udayanga.uni_timetable.AbstractTest;
import com.udayanga.uni_timetable.model.User;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AuthControllerTest extends AbstractTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    String accessToken;
    @Test
    void registerUser() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/api/auth/signup";
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("testUser@test.com");
        user.setPassword("testtest");
        String inputJson = super.mapToJson(user);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"message\":\"User registered successfully!\"}",content);
    }

    @Test
    void authenticateUser() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String uri = "http://localhost:8080/api/auth/signin";
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testtest");
        String inputJson = super.mapToJson(user);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        accessToken = new JSONObject(mvcResult.getResponse().getContentAsString()).getString("accessToken");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

//    @Test
//    void userUnauthorizedAccessToAdminBoard() throws Exception{
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity().build();
//        String uri = "http://localhost:8080/api/test/admin";
//
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).header("Authorization","Bearer "+accessToken)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//    }

//    @Test
//    void deleteUser() throws Exception{
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        String uri = "http://localhost:8080/api/auth/delete/testUser@test.com";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(204, status);
////        String content = mvcResult.getResponse().getContentAsString();
////        assertEquals(content, "User is deleted successsfully");
//    }


}
package com.spajam2021_pre4.spajam2021_pre4.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpjcontrollerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_shouldSetSettings() throws Exception {
        MvcResult result = mockMvc.perform(post("/arrival").param("url","sample.com")).andExpect(status().isOk()).andReturn();
        assertEquals("sample.com",result.getResponse().getContentAsString());
    }

    @Test
    void test_shouldGetArrival() throws Exception {
        MvcResult result = mockMvc.perform(get("/arrival")).andExpect(status().isOk()).andReturn();
        assertEquals("getArrival",result.getResponse().getContentAsString());
    }
}
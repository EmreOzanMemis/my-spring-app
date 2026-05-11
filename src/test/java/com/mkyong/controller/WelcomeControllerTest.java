package com.mkyong.controller;

import com.mkyong.StartWebApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartWebApplication.class)
@AutoConfigureMockMvc
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WelcomeController welcomeController;

    @Before
    public void resetTasks() {
        ReflectionTestUtils.setField(
                welcomeController,
                "tasks",
                new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g")));
    }

    @Test
    public void welcomePageStillWorks() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(content().string(containsString("Spring Boot")))
                .andExpect(content().string(containsString("deployed onto Azure with GitHub Actions")))
                .andExpect(content().string(containsString("Add a task")))
                .andExpect(content().string(containsString(">a<")));
    }

    @Test
    public void addTaskAddsTaskToInMemoryList() throws Exception {
        mockMvc.perform(post("/tasks").param("task", "Write controller test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Write controller test")));
    }

    @Test
    public void keywordFilterShowsOnlyMatchingTasks() throws Exception {
        mockMvc.perform(post("/tasks").param("task", "Buy milk"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/tasks").param("task", "Deploy app"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/").param("keyword", "BUY"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Buy milk")))
                .andExpect(content().string(not(containsString("Deploy app"))));
    }
}

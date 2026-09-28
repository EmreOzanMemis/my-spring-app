package com.mkyong.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class WelcomeControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        WelcomeController controller = new WelcomeController();
        ReflectionTestUtils.setField(controller, "message", "Welcome from test");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void mainShouldExposeDefaultTasks() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("message", "Welcome from test"))
                .andExpect(model().attribute("filter", ""))
                .andExpect(model().attribute("tasks", hasSize(3)))
                .andExpect(model().attribute("tasks", hasItem("Prepare Spring Boot demo")));
    }

    @Test
    public void addTaskShouldAppendTrimmedTaskAndRedirect() throws Exception {
        mockMvc.perform(post("/tasks").param("task", "  Review demo flow  "))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("taskAdded", true));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasItem("Review demo flow")));
    }

    @Test
    public void addTaskShouldIgnoreBlankValues() throws Exception {
        mockMvc.perform(post("/tasks").param("task", "   "))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasSize(3)));
    }

    @Test
    public void mainShouldFilterTasksByKeyword() throws Exception {
        mockMvc.perform(get("/").param("filter", "view"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("filter", "view"))
                .andExpect(model().attribute("tasks", contains("Show Thymeleaf view")));
    }

    @Test
    public void helloShouldKeepExistingBehaviorAndExposeTasks() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Copilot"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("message", "Copilot"))
                .andExpect(model().attribute("tasks", hasSize(3)));
    }
}

package com.mkyong.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homePageReturnsTasksInModel() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attribute("tasks", hasItem("Buy groceries")));
    }

    @Test
    public void addTaskRedirectsAndPersistsTask() throws Exception {
        mockMvc.perform(post("/tasks").param("task", "Test new task"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasItem("Test new task")));
    }

    @Test
    public void filterReturnsOnlyMatchingTasks() throws Exception {
        mockMvc.perform(get("/").param("filter", "groceries"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasItem("Buy groceries")))
                .andExpect(model().attribute("tasks", not(hasItem("Read a book"))));
    }

    @Test
    public void emptyTaskIsNotAdded() throws Exception {
        mockMvc.perform(post("/tasks").param("task", "   "))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/"))
                .andExpect(model().attribute("tasks", not(hasItem("   "))));
    }
}

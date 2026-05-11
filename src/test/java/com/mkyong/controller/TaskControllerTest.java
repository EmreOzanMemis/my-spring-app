package com.mkyong.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTasks_returnsTasksView() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks"))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attribute("keyword", ""));
    }

    @Test
    public void testGetTasks_containsDefaultTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasItem("Set up Spring Boot project")));
    }

    @Test
    public void testAddTask_redirectsToTasksList() throws Exception {
        mockMvc.perform(post("/tasks/add")
                        .param("task", "My new test task"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));
    }

    @Test
    public void testAddTask_appearsInList() throws Exception {
        mockMvc.perform(post("/tasks/add")
                        .param("task", "UniqueTask_XYZ"));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasItem("UniqueTask_XYZ")));
    }

    @Test
    public void testFilterTasks_byKeyword() throws Exception {
        mockMvc.perform(post("/tasks/add")
                        .param("task", "FilterableTask_ABC"));

        mockMvc.perform(get("/tasks").param("keyword", "FilterableTask"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", hasItem("FilterableTask_ABC")))
                .andExpect(model().attribute("keyword", "FilterableTask"));
    }

    @Test
    public void testFilterTasks_noMatch_returnsEmptyList() throws Exception {
        mockMvc.perform(get("/tasks").param("keyword", "zzz_no_match_zzz"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", empty()));
    }

    @Test
    public void testAddTask_blankTask_notAdded() throws Exception {
        mockMvc.perform(post("/tasks/add")
                        .param("task", "   "))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));

        // blank tasks should not be added; the list should not grow
        mockMvc.perform(get("/tasks").param("keyword", "   "))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tasks", empty()));
    }
}

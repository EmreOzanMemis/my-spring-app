package com.mkyong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final List<String> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add("Set up Spring Boot project");
        tasks.add("Add Thymeleaf templates");
        tasks.add("Deploy to Azure App Service");
    }

    @GetMapping
    public String listTasks(
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            Model model) {

        List<String> filtered;
        if (keyword.isEmpty()) {
            filtered = tasks;
        } else {
            String lower = keyword.toLowerCase();
            filtered = tasks.stream()
                    .filter(t -> t.toLowerCase().contains(lower))
                    .collect(Collectors.toList());
        }

        model.addAttribute("tasks", filtered);
        model.addAttribute("keyword", keyword);
        return "tasks";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam(name = "task") String task) {
        String trimmed = task.trim();
        if (!trimmed.isEmpty()) {
            tasks.add(trimmed);
        }
        return "redirect:/tasks";
    }
}

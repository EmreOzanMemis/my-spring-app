package com.mkyong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g"));

    @GetMapping("/")
    public String main(
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            Model model) {
        populateModel(model, message, keyword);
        
        System.out.println("Example log from std out.");
        System.out.println("Another example log from std out!");

        return "welcome"; //view
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam(name = "task", required = false, defaultValue = "") String task) {
        String trimmedTask = task.trim();
        if (!trimmedTask.isEmpty()) {
            tasks.add(trimmedTask);
        }

        return "redirect:/";
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

        populateModel(model, name, "");
        
        System.out.println("Hello controller, name = "+name); 

        return "welcome"; //view
    }

    private void populateModel(Model model, String currentMessage, String keyword) {
        model.addAttribute("message", currentMessage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("tasks", filterTasks(keyword));
    }

    private List<String> filterTasks(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ENGLISH).trim();

        if (normalizedKeyword.isEmpty()) {
            return tasks;
        }

        return tasks.stream()
                .filter(task -> task.toLowerCase(Locale.ENGLISH).contains(normalizedKeyword))
                .collect(Collectors.toList());
    }

}

package com.mkyong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private final List<String> tasks = Collections.synchronizedList(new ArrayList<>(Arrays.asList(
            "Prepare Spring Boot demo",
            "Show Thymeleaf view",
            "Add one more task live"
    )));

    @GetMapping("/")
    public String main(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                       Model model) {
        populateModel(model, message, filter);
        
        System.out.println("Example log from std out.");
        System.out.println("Another example log from std out!");

        return "welcome"; //view
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter,
            Model model) {

        populateModel(model, name, filter);
        
        System.out.println("Hello controller, name = "+name); 

        return "welcome"; //view
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam(name = "task", required = false, defaultValue = "") String task,
                          RedirectAttributes redirectAttributes) {
        String trimmedTask = task.trim();
        if (!trimmedTask.isEmpty()) {
            tasks.add(trimmedTask);
            redirectAttributes.addFlashAttribute("taskAdded", true);
        }
        return "redirect:/";
    }

    private void populateModel(Model model, String currentMessage, String filter) {
        model.addAttribute("message", currentMessage);
        model.addAttribute("filter", filter);
        model.addAttribute("tasks", filterTasks(filter));
    }

    private List<String> filterTasks(String filter) {
        String normalizedFilter = filter == null ? "" : filter.trim().toLowerCase(Locale.ROOT);
        synchronized (tasks) {
            return tasks.stream()
                    .filter(task -> normalizedFilter.isEmpty()
                            || task.toLowerCase(Locale.ROOT).contains(normalizedFilter))
                    .collect(Collectors.toList());
        }
    }

}

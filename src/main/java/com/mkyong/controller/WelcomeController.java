package com.mkyong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = new ArrayList<>(java.util.Arrays.asList("Buy groceries", "Read a book", "Write unit tests"));

    @GetMapping("/")
    public String main(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                       Model model) {
        model.addAttribute("message", message);
        model.addAttribute("filter", filter);

        if (filter.isEmpty()) {
            model.addAttribute("tasks", tasks);
        } else {
            List<String> filtered = new ArrayList<>();
            for (String task : tasks) {
                if (task.toLowerCase().contains(filter.toLowerCase())) {
                    filtered.add(task);
                }
            }
            model.addAttribute("tasks", filtered);
        }

        System.out.println("Example log from std out.");
        System.out.println("Another example log from std out!");

        return "welcome"; //view
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam(name = "task", required = false, defaultValue = "") String task,
                          RedirectAttributes redirectAttributes) {
        String trimmed = task.trim();
        if (!trimmed.isEmpty()) {
            tasks.add(trimmed);
        }
        return "redirect:/";
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

        model.addAttribute("message", name);
        
        System.out.println("Hello controller, name = "+name); 

        return "welcome"; //view
    }

}

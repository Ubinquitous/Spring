package com.example.todolist.controller;

import com.example.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    private final TodoService todoService;

    @GetMapping(value = {"", "/"})
    public String index(Model model) {
        model.addAttribute("response", todoService.findAll());
        return "index";
    }
}

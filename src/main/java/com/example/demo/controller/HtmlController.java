package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {
    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        String name = "Spring Boot";
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping(value = "/insert")
    public String insert(Model model) {
        model.addAttribute("user", new User());
        return "insert";
    }
}
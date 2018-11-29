package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {
    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        String name = "jiangbei";
        model.addAttribute("name", name);
        return "index";
    }
}
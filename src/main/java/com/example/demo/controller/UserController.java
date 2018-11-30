package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService iUserService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/add")
    public String insertUser(User user){
        logger.info(user.toString());
        iUserService.insertUser(user);
        return "redirect:/selectAll";
    }

    @RequestMapping("/selectAll")
    public String selectAll (Model model){
        List<User> userList = iUserService.selectAll();
        model.addAttribute("userList", userList);
        return "list";
    }

    @RequestMapping("/delete")
    public String delete(int id){
        iUserService.delete(id);
        return "redirect:/selectAll";
    }


}

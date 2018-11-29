package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/insert")
    public void insertUser(User user){
        user.setAge(23);
        user.setName("张三");
        user.setSex("1");
        iUserService.insertUser(user);
        logger.info("cehsi");
    }
}

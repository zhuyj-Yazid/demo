package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface IUserService{

    void insertUser(User user);

    List<User> selectAll();

    void delete(int id);

}
package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        logger.info("insert:"+ user.toString());
        userMapper.insert(user);
    }

    @Override
    public List<User> selectAll() {
        List<User> userList = userMapper.selectAll();
        logger.info("selectAll:"+ userList.size());
        return userList;
    }

    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }
}

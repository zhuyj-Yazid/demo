package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);//FATAL > ERROR > WARN > INFO > DEBUG > TRACE  -->
        logger.error("测试error级别日志");
        logger.warn("测试warn级别日志");
        logger.info("测试info级别日志");
        logger.debug("测试debug级别日志");
        logger.trace("测试trace级别日志");
    }
}

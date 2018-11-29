package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    //@Insert("INSERT INTO USER ( name, sex, age ) VALUES ( #{name}, #{sex}, #{age} )")
    void insert(User record);
}
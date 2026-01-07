package com.example.java_practice.service.impl;

import com.example.java_practice.entity.User;
import com.example.java_practice.mapper.UserMapper;
import com.example.java_practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    // 构造器注入（推荐）
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(User user) {
        // 先留空，下一步我们再写逻辑
    }
}

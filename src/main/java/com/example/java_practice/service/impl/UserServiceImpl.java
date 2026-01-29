package com.example.java_practice.service.impl;

import java.math.BigDecimal;
import com.example.java_practice.entity.User;
import com.example.java_practice.entity.Course;
import com.example.java_practice.mapper.UserMapper;
import com.example.java_practice.service.UserService;
import org.springframework.stereotype.Service;
import com.example.java_practice.entity.MessageReturn;
import com.example.java_practice.mapper.CourseMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    // 构造器注入（推荐）
    public UserServiceImpl(UserMapper userMapper, CourseMapper courseMapper) {
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public MessageReturn<Object> register(User user) {
        // 简单将注册人的身份设置为0，即学生
        user.setRole(0);

        // 简单将注册人余额设置为0
        user.setBalance(BigDecimal.ZERO);

        userMapper.insert(user);

        // 返回
        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData(null);
        return re;
    }

    // 用于查询所有用户
    @Override
    public MessageReturn<Object> listUsers() {
        // selectList(null);用于查询全表，因为条件处是null
        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData(userMapper.selectList(null));
        return re;
    }

    // 用于查询指定 id的用户
    // User对象是在entity中定义的，包括里面所含有的所有变量
    @Override
    public MessageReturn<Object> getUserById(Long id) {
        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData(userMapper.selectById(id));
        return re;
    }

    // 用于根据 id查询其所选课程
    @Override
    public MessageReturn<Object> getStudentCourse(Long id) {
        User student = userMapper.selectById(id);

        if (student == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("该用户不存在，无法查询");
            re.setData(null);
            return re;
        }

        if (student.getRole() == 1) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("该用户是老师，无法查询");
            re.setData(null);
            return re;
        }

        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData( courseMapper.selectCoursesByUserId(id));
        return re;
    }
}

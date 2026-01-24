package com.example.java_practice.service.impl;

import java.math.BigDecimal;
import com.example.java_practice.entity.User;
import com.example.java_practice.entity.Course;
import com.example.java_practice.mapper.UserMapper;
import com.example.java_practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.java_practice.mapper.CourseMapper;

import javax.annotation.Resource;
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
    public void register(User user) {
        // 简单将注册人的身份设置为0，即学生
        user.setRole(0);

        // 简单将注册人余额设置为0
        user.setBalance(BigDecimal.ZERO);

        userMapper.insert(user);
    }

    // 用于查询所有用户
    @Override
    public List<User> listUsers() {
        // selectList(null);用于查询全表，因为条件处是null
        return userMapper.selectList(null);
    }

    // 用于查询指定 id的用户
    // User对象是在entity中定义的，包括里面所含有的所有变量
    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    // 用于根据 id查询其所选课程
    @Override
    public List<Course> getStudentCourse(Long id) {
        User student = userMapper.selectById(id);

        if (student == null) {
            throw new RuntimeException("该用户不存在，无法查询");
        }

        if (student.getRole() == 1) {
            throw new RuntimeException("该用户是老师，无法查询");
        }

        return courseMapper.selectCoursesByUserId(id);

    }
}

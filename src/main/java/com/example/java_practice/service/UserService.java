package com.example.java_practice.service;

import com.example.java_practice.entity.User;
import com.example.java_practice.entity.Course;
import com.example.java_practice.mapper.CourseMapper;
import com.example.java_practice.entity.MessageReturn;
// 引入 list 集合类型
import java.util.List;

public interface UserService {

    MessageReturn<Object> register(User user);

    // 声明一个新能力：获取所有用户列表
    // 方法名listUsers 返回值类型List<User>，用户集合。 无参数
    // <User>是一个泛型，表示这个List中只能放User对象
    MessageReturn<Object> listUsers();

    MessageReturn<Object> getUserById(Long id);

    MessageReturn<Object> getStudentCourse(Long studentId);
}

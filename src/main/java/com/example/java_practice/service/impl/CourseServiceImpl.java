package com.example.java_practice.service.impl;

import com.example.java_practice.mapper.CourseMapper;
import com.example.java_practice.mapper.UserMapper;
import com.example.java_practice.service.CourseService;
import com.example.java_practice.entity.Course;
import com.example.java_practice.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;

    public CourseServiceImpl(CourseMapper courseMapper,UserMapper userMapper) {
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void createCourse(Course course) {
        // 获取 teacherId
        Long teacherId = course.getTeacherId();

        User teacher = userMapper.selectById(teacherId);

        // 判断教师是否存在
        if (teacher == null) {
            throw new RuntimeException("教师不存在，无法创建课程");
        }

        // 判断用户是不是老师
        if (teacher.getRole() != 1) {
            throw new RuntimeException("该用户不是老师，不能创建课程");
        }

        // 设置默认值
        course.setStudentCount(0);
        course.setLikes(1L);

        // 插入
        courseMapper.insert(course);
    }

}

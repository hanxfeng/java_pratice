package com.example.java_practice.service;

import com.example.java_practice.entity.Course;
import com.example.java_practice.entity.User;
import com.example.java_practice.entity.CourseStudent;
import java.util.List;

public interface CourseService {
    void createCourse(Course course);
    void studentCourseSelect(Long userId,Long courseId);
}

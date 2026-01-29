package com.example.java_practice.controller;

import com.example.java_practice.dto.studentCourseSelectRequest;
import com.example.java_practice.entity.Course;
import com.example.java_practice.service.CourseService;
import org.springframework.web.bind.annotation.*;
import com.example.java_practice.entity.MessageReturn;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 用于教师创建课程
    @PostMapping("/creatCourse")
    public MessageReturn<Object> creatCourse (@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // 用于学生进行选课
    @PostMapping("/select")
    public MessageReturn<Object> studentCourseSelect (@RequestBody studentCourseSelectRequest request) {
        return courseService.studentCourseSelect(
                request.getUserId(),
                request.getCourseId()
        );
    }
}

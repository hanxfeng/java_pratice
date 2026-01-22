package com.example.java_practice.controller;

import com.example.java_practice.dto.studentCourseSelectRequest;
import com.example.java_practice.entity.Course;
import com.example.java_practice.service.CourseService;
import org.springframework.web.bind.annotation.*;
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
    public String creatCourse (@RequestBody Course course) {
        courseService.createCourse(course);
        return "success";
    }

    // 用于学生进行选课
    @PostMapping("/select")
    public String studentCourseSelect (@RequestBody studentCourseSelectRequest request) {
        courseService.studentCourseSelect(
                request.getUserId(),
                request.getCourseId()
        );
        return "success";
    }
}

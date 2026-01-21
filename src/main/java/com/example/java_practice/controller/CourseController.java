package com.example.java_practice.controller;

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

    @PostMapping("/creatCourse")
    public String creatCoures (@RequestBody Course course) {
        courseService.createCourse(course);
        return "success";
    }
}

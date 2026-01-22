package com.example.java_practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("course_student")
public class CourseStudent {
    private Long userId;
    private Long courseId;
    private LocalDateTime createdAt;
}

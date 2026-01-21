package com.example.java_practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("courses")
public class Course {
    private Long id;
    private String title;
    private String description;
    private String coverUrl;
    private BigDecimal price;
    private Long teacherId;
    private int studentCount;
    private Long likes;
    private LocalDateTime createdAt;
}

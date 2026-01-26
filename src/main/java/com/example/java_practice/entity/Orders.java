package com.example.java_practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("Orders")
public class Orders {
    private Long id;
    private Long userId;
    private Long courseId;
    private BigDecimal amount;
    private int status;
    private LocalDateTime paidAt;
}

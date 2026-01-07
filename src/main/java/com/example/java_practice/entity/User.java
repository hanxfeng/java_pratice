package com.example.java_practice.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

/*
@Data 是 Lombok 的"大礼包"注解，等价于：
@Getter        // 为所有字段生成getter方法
@Setter        // 为所有非final字段生成setter方法
@ToString      // 生成toString()方法
@EqualsAndHashCode // 生成equals()和hashCode()方法
@RequiredArgsConstructor // 生成包含final字段的构造函数
 */

// 使用@TableName可以明确指定表名，这里指名这个类对应数据库中的users表
@Data
@TableName("users")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
    private BigDecimal balance;
    private String createdAt;
}

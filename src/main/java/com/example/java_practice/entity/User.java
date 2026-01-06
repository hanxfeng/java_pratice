package com.example.java_practice.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;


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

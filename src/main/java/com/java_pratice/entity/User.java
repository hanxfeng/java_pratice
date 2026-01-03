package com.java_pratice.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Integer role;
    private BigDecimal balance;
    private LocalDate createdAT;
}

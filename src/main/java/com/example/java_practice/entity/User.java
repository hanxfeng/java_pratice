package com.example.java_practice.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
@Data 是 Lombok 的"大礼包"注解，等价于：
@Getter        // 为所有字段生成getter方法
@Setter        // 为所有非final字段生成setter方法
@ToString      // 生成toString()方法
@EqualsAndHashCode // 生成equals()和hashCode()方法
@RequiredArgsConstructor // 生成包含final字段的构造函数
 */

// 使用@TableName可以明确指定表名，这里指名这个类对应数据库中的users表

/* SQL变量转java变量数据类型转换：
    SQL                 java                    说明
    BIGINT      ->      Long                    常用于 id、订单号
    Int         ->      Integer/int             普通整数
    TINYINT     ->      Integer/Boolean/int     常用作状态位
    SMALLINT    ->      Integer/int             小整数
    DECIMAL(M,N)->      BigDecimal              金额、精度要求高
    FLOAT       ->      Float                   不精确浮点
    DOUBLE      ->      Double                  不精确浮点
    VARCHAR/CHAR/TEXT/LONGTEXT  ->      String  各种字符串
    DATE        ->      LocalDate               仅日期
    TIME        ->      LocalTime               仅时间
    DATETIME    ->      LocalDateTime           日期 + 时间
    TIMESTAMP   ->      LocalDateTime           时间戳

 */

@Data
@TableName("users")
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int role;
    private BigDecimal balance;
    private String createdAt;

}

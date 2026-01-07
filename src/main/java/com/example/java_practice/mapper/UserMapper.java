package com.example.java_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java_practice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 在此对数据库进行操作
public interface UserMapper extends BaseMapper<User> {
}

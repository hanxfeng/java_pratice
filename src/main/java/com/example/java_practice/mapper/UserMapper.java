package com.example.java_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java_practice.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

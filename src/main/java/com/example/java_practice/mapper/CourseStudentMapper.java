package com.example.java_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java_practice.entity.CourseStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public interface CourseStudentMapper extends BaseMapper<CourseStudent>{
}

package com.example.java_practice.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java_practice.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public interface CourseMapper extends BaseMapper<Course>{
    @Select("""
        SELECT c.*
        FROM courses c
        JOIN course_student cs ON c.id = cs.course_id
        WHERE cs.user_id = #{userId}
    """)
    // 这样 selectCoursesByUserId就会调用注解中的SQL进行查询，进行复杂查询时通常使用这种方法
    List<Course> selectCoursesByUserId(@Param("userId") Long userId);
}

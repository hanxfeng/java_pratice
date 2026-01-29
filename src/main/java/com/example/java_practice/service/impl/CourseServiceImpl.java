package com.example.java_practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.java_practice.mapper.CourseMapper;
import com.example.java_practice.mapper.CourseStudentMapper;
import com.example.java_practice.mapper.UserMapper;
import com.example.java_practice.service.CourseService;
import com.example.java_practice.entity.Course;
import com.example.java_practice.entity.User;
import com.example.java_practice.entity.CourseStudent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.java_practice.entity.MessageReturn;
import java.time.LocalDateTime;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final CourseStudentMapper courseStudentMapper;

    public CourseServiceImpl(CourseMapper courseMapper, UserMapper userMapper, CourseStudentMapper courseStudentMapper) {
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
        this.courseStudentMapper = courseStudentMapper;
    }

    @Override
    public MessageReturn<Object> createCourse(Course course) {
        // 获取 teacherId
        Long teacherId = course.getTeacherId();

        User teacher = userMapper.selectById(teacherId);

        // 判断教师是否存在
        if (teacher == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("教师不存在，无法创建课程");
            re.setData(null);
            return re;
        }

        // 判断用户是不是老师
        if (teacher.getRole() != 1) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("该用户不是老师，无法创建课程");
            re.setData(null);
            return re;
        }

        // 设置默认值
        course.setStudentCount(0);
        course.setLikes(0L);

        // 插入
        courseMapper.insert(course);

        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData(null);
        return re;
    }

    @Transactional  // 带有这个注解代表里面的 SQL要么一起成功要么一起失败
    @Override
    public MessageReturn<Object> studentCourseSelect(Long userId,Long courseId) {

        // 判断用户是否存在
        if (userMapper.selectById(userId) == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("用户不存在，无法选课");
            re.setData(null);
            return re;
        }

        // 判断课程是否存在
        if (courseMapper.selectById(courseId) == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("课程不存在，无法选课");
            re.setData(null);
            return re;
        }

        // 判断用户是否是学生
        if (userMapper.selectById(userId).getRole() == 1) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("用户为教师，无法选课");
            re.setData(null);
            return re;
        }

        // 判断是否重复进行选课
        LambdaQueryWrapper<CourseStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseStudent::getUserId, userId)
                .eq(CourseStudent::getCourseId, courseId);

        CourseStudent cs = courseStudentMapper.selectOne(wrapper);

        if (cs != null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("已选过该课程，无法选课");
            re.setData(null);
            return re;
        }

        // 获取当前时间准备插入
        LocalDateTime now = LocalDateTime.now();
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setUserId(userId);
        courseStudent.setCourseId(courseId);
        courseStudent.setCreatedAt(now);

        // 插入 courseStudent表
        courseStudentMapper.insert(courseStudent);

        // 更新 course表
        Course c = courseMapper.selectById(courseId);
        c.setStudentCount(c.getStudentCount() + 1);
        courseMapper.updateById(c);

        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData(null);
        return re;
    }
}

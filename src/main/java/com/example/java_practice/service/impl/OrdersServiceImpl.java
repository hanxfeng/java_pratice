package com.example.java_practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.java_practice.entity.Course;
import com.example.java_practice.entity.CourseStudent;
import com.example.java_practice.entity.Orders;
import com.example.java_practice.entity.User;
import com.example.java_practice.mapper.CourseMapper;
import com.example.java_practice.mapper.CourseStudentMapper;
import com.example.java_practice.mapper.OrdersMapper;
import com.example.java_practice.mapper.UserMapper;
import com.example.java_practice.service.OrdersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersMapper ordersMapper;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final CourseStudentMapper courseStudentMapper;

    public OrdersServiceImpl(CourseMapper courseMapper, UserMapper userMapper, CourseStudentMapper courseStudentMapper, OrdersMapper ordersMapper) {
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
        this.courseStudentMapper = courseStudentMapper;
        this.ordersMapper = ordersMapper;
    }

    @Transactional
    @Override
    public void courseBuy(Long userId, Long courseId) {
        User user = userMapper.selectById(userId);
        Course course = courseMapper.selectById(courseId);

        // 判断用户是否存在
        if (user == null) {
            throw new RuntimeException("该用户不存在，无法购买");
        }

        // 判断课程是否存在
        if (course == null) {
            throw new RuntimeException("课程不存在，无法购买");
        }

        // 判断是否是学生
        if (user.getRole() == 1) {
            throw new RuntimeException("该用户不是学生，无法购买");
        }

        // 判断是否购买过该课程
        LambdaQueryWrapper<CourseStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseStudent::getUserId, userId)
                .eq(CourseStudent::getCourseId, courseId);

        CourseStudent cs = courseStudentMapper.selectOne(wrapper);

        if (cs != null) {
            throw new RuntimeException("已购买过该课程，无法购买");
        }

        // 判断余额是否足够
        BigDecimal price = course.getPrice();
        BigDecimal balance = user.getBalance();

        if (price.compareTo(balance) > 0) {
            throw new RuntimeException("余额不足，无法购买");
        }

        // 创建 Orders对象
        Orders order = new Orders();
        order.setUserId(userId);
        order.setCourseId(courseId);
        order.setAmount(price);
        order.setStatus(0);
        LocalDateTime now = LocalDateTime.now();
        order.setPaidAt(now);

        // 在余额中扣除购买课程的钱
        User u = userMapper.selectById(userId);
        u.setBalance(u.getBalance().subtract(price));

        // 修改支付状态
        order.setStatus(1);

        // 更新余额
        userMapper.updateById(u);

        // 插入 order
        ordersMapper.insert(order);

        // 插入选课表
        CourseStudent csNew = new CourseStudent();
        csNew.setUserId(userId);
        csNew.setCourseId(courseId);
        csNew.setCreatedAt(LocalDateTime.now());
        courseStudentMapper.insert(csNew);
    }

    public List<Orders> selectOrderByUserId (Long userId) {
        // 检查用户是否存在
        if (userMapper.selectById(userId) == null) {
            throw new RuntimeException("用户不存在");
        }

        // 查询订单并返回
        return ordersMapper.selectOrdersByUserId(userId);
    }
}

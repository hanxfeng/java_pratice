package com.example.java_practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.example.java_practice.entity.MessageReturn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public MessageReturn<Object> courseBuy(Long userId, Long courseId) {
        User user = userMapper.selectById(userId);
        Course course = courseMapper.selectById(courseId);

        // 判断用户是否存在
        if (user == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("该用户不存在，无法购买");
            re.setData(null);
            return re;
        }

        // 判断课程是否存在
        if (course == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("课程不存在，无法购买");
            re.setData(null);
            return re;
        }

        // 判断是否是学生
        if (user.getRole() == 1) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("该用户不是学生，无法购买");
            re.setData(null);
            return re;
        }

        // 判断是否购买过该课程
        LambdaQueryWrapper<CourseStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseStudent::getUserId, userId)
                .eq(CourseStudent::getCourseId, courseId);

        CourseStudent cs = courseStudentMapper.selectOne(wrapper);

        if (cs != null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("已购买该课程，无法购买");
            re.setData(null);
            return re;
        }

        // 判断余额是否足够
        BigDecimal price = course.getPrice();
        BigDecimal balance = user.getBalance();

        if (price.compareTo(balance) > 0) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("余额不足在，无法购买");
            re.setData(null);
            return re;
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

        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData(null);
        return re;
    }

    @Override
    public MessageReturn<Object> selectOrderByUserId (Long userId) {
        // 检查用户是否存在
        if (userMapper.selectById(userId) == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("该用户不存在");
            re.setData(null);
            return re;
        }

        // 查询订单并返回
        List<Orders> ord = ordersMapper.selectOrdersByUserId(userId);
        // selectOrdersByUserId 方法即使没有查到数据也不会返回null，因此需要使用isEmpty()判断
        // isEmpty()用于判断集合类型（List，Map等）是否为空，或字符串长度是否为0
        if (ord.isEmpty()) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(0);
            re.setMessage("success");
            re.setData(Collections.emptyList());
            return re;
        }
        else {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(0);
            re.setMessage("success");
            re.setData(ord);
            return re;
        }
    }

    @Transactional
    @Override
    public MessageReturn<Object> refundByOrderId (Long orderId) {
        // 查询订单
        Orders refundOrder = ordersMapper.selectById(orderId);

        if (refundOrder == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("请求退款的订单不存在");
            re.setData(null);
            return re;
        }

        // 查询订单是否未支付或已退款
        if (refundOrder.getStatus() != 1) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("订单未支付或已退款，无法退款");
            re.setData(null);
            return re;
        }

        // 获取课程 id和用户 id
        Long userId = refundOrder.getUserId();
        Long courseId = refundOrder.getCourseId();

        // 检查用户是否存在
        if (userMapper.selectById(userId) == null) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("该用户不存在，无法退款");
            re.setData(null);
            return re;
        }


        // 删除选课表中的数据
        int rows = courseStudentMapper.delete(
                new QueryWrapper<CourseStudent>()
                        .eq("course_id", courseId)
                        .eq("user_id", userId)
        );
        if (rows == 0) {
            MessageReturn<Object> re = new MessageReturn<>();
            re.setCode(1);
            re.setMessage("数据不存在，无法删除");
            re.setData(null);
            return re;
        }

        // 修改订单状态
        refundOrder.setStatus(2);
        ordersMapper.updateById(refundOrder);

        // 回滚用户余额
        User user = userMapper.selectById(userId);
        BigDecimal balance = refundOrder.getAmount();
        user.setBalance(user.getBalance().add(balance));
        userMapper.updateById(user);

        MessageReturn<Object> re = new MessageReturn<>();
        re.setCode(0);
        re.setMessage("success");
        re.setData(null);
        return re;
    }
}

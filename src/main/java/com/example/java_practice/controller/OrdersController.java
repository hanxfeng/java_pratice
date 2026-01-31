package com.example.java_practice.controller;


import com.example.java_practice.dto.CourseBuyRequest;
import com.example.java_practice.entity.Orders;
import com.example.java_practice.service.OrdersService;
import org.springframework.web.bind.annotation.*;
import com.example.java_practice.entity.MessageReturn;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // 用于进行课程购买
    @PostMapping("/buy")
    public MessageReturn<Object> courseBuy (@RequestBody CourseBuyRequest request) {
        ordersService.courseBuy(request.getUserId(), request.getCourseId());
        return MessageReturn.success();
    }

    // 根据用户 id查询其订单列表
    @GetMapping("/user/{userId}")
    public MessageReturn<Object> selectOrderByUserId (@PathVariable Long userId) {
        List<Orders> ord = ordersService.selectOrderByUserId(userId);
        return MessageReturn.success(ord);
    }

    // 根据给的orderId 进行退款
    @PostMapping("/refund")
    public MessageReturn<Object> refundByOrderId (@RequestBody Long orderId) {
        ordersService.refundByOrderId(orderId);
        return MessageReturn.success();
    }
}

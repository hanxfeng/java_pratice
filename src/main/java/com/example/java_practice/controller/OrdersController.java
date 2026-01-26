package com.example.java_practice.controller;


import com.example.java_practice.dto.CourseBuyRequest;
import com.example.java_practice.service.OrdersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // 用于进行课程购买
    @PostMapping("/buy")
    public String courseBuy (@RequestBody CourseBuyRequest request) {
        ordersService.courseBuy(request.getUserId(), request.getCourseId());
        return "success";
    }
}

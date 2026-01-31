package com.example.java_practice.service;

import java.util.List;
import java.util.Objects;
import com.example.java_practice.entity.Orders;
import com.example.java_practice.entity.MessageReturn;

public interface OrdersService {
    void courseBuy(Long userId, Long courseId);
    List<Orders> selectOrderByUserId(Long userId);
    void refundByOrderId(Long orderId);
}

package com.example.java_practice.service;

import java.util.List;
import com.example.java_practice.entity.Orders;

public interface OrdersService {
    void courseBuy(Long userId,Long courseId);
    List<Orders> selectOrderByUserId(Long userId);
    String refundByOrderId(Long orderId);
}

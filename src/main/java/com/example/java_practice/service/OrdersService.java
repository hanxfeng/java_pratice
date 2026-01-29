package com.example.java_practice.service;

import java.util.List;
import java.util.Objects;
import com.example.java_practice.entity.Orders;
import com.example.java_practice.entity.MessageReturn;

public interface OrdersService {
    MessageReturn<Object> courseBuy(Long userId, Long courseId);
    MessageReturn<Object> selectOrderByUserId(Long userId);
    MessageReturn<Object> refundByOrderId(Long orderId);
}

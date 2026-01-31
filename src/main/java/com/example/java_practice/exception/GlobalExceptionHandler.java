package com.example.java_practice.exception;

import com.example.java_practice.entity.MessageReturn;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理业务异常
    @ExceptionHandler(BizException.class)
    public MessageReturn<Object> handleBizException(BizException e) {
        return MessageReturn.error(e.getMessage());
    }

    // 兜底异常（防止 500 裸奔）
    @ExceptionHandler(Exception.class)
    public MessageReturn<Object> handleException(Exception e) {
        e.printStackTrace(); // 开发阶段保留
        return MessageReturn.error("系统异常，请稍后重试");
    }
}

package com.example.java_practice.exception;

public class BizException extends RuntimeException {
    public BizException(String message) {
        // 把异常信息交给父类保存，使 getMessage()、日志打印、堆栈信息能正常显示错误描述
        super(message);
    }
}

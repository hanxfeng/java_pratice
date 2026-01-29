package com.example.java_practice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageReturn<T> {
    private int code;
    private String message;
    private T data;

    public MessageReturn() {
    }

    public MessageReturn(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

}

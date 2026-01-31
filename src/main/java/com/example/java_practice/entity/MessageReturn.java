package com.example.java_practice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageReturn<T> {
    private int code;
    private String message;
    private T data;

    public static <T> MessageReturn<T> success() {
        MessageReturn<T> r = new MessageReturn<>();
        r.setCode(0);
        r.setMessage("success");
        r.setData(null);
        return r;
    }

    public static <T> MessageReturn<T> success(T data) {
        MessageReturn<T> r = new MessageReturn<>();
        r.setCode(0);
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    public static <T> MessageReturn<T> error(String message) {
        MessageReturn<T> r = new MessageReturn<>();
        r.setCode(1);
        r.setMessage(message);
        r.setData(null);
        return r;
    }

}

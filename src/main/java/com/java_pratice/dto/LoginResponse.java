package com.java_pratice.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean success;
    private String message;
    private String token;

    //成功登录的静态方法
    public static LoginResponse success(String token){
        LoginResponse response = new LoginResponse();
        response.setSuccess(true);
        response.setMessage("登录成功");
        response.setToken(token);
        return response;
    }

    // 登录失败的静态方法
    public static LoginResponse error(String message) {
        LoginResponse response = new LoginResponse();
        response.setSuccess(false);
        response.setMessage(message);
        response.setToken(null);
        return response;
    }
}

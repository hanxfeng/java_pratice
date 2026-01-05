package com.java_pratice.service;

import com.java_pratice.entity.User;
import com.java_pratice.repository.UserRepository;
import com.java_pratice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return token字符串，如果登录失败返回null
     */
    public String login(String email, String password) {
        // 1. 查询用户是否存在
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);

        if (!userOptional.isPresent()) {
            return null; // 用户不存在或密码错误
        }

        // 2. 获取用户信息
        User user = userOptional.get();

        // 3. 生成JWT Token
        String token = jwtUtil.generateToken(user.getId());

        return token;
    }

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户对象
     */
    public User getUserInfo(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }
}
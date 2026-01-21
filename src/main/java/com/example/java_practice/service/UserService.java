package com.example.java_practice.service;

import com.example.java_practice.entity.User;

// 引入 list 集合类型
import java.util.List;
public interface UserService {

    void register(User user);

    // 声明一个新能力：获取所有用户列表
    // 方法名listUsers 返回值类型List<User>，用户集合。 无参数
    // <User>是一个泛型，表示这个List中只能放User对象
    List<User> listUsers();

    User getUserById(Long id);

}

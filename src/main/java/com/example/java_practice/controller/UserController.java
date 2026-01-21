package com.example.java_practice.controller;

import com.example.java_practice.entity.User;
import com.example.java_practice.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users") // 给下面的所有接口添加/users前缀
public class UserController {  //定义类UserController
    // private - 访问修饰符 限制访问范围，只能在声明它的类内部访问 外部完全不可见：其他类无法直接访问这个变量
    // final - 不可变修饰符  一次性赋值：只能被赋值一次  编译时检查：编译器确保不会二次赋值
    //UserService 引用的类名
    // userService  定义的变量名
    private final UserService userService;

    // public 该关键字定义的方法任何地方都可以访问
    public UserController(UserService userService) {
        //上文定义的变量userService与参数userService重名，java中重名变量优先使用参数
        //使用this关键字表示这个变量指上面定义的成员变量，否则会出现给参数userService赋值为参数userService的情况
        this.userService = userService;
    }

    //定义一个POST类型的端口/register
    @PostMapping("/register")
    //定义一个返回字符串的方法register
    //@RequestBody用于将请求体中的JSON/XML转换为Java对象
    public String register(@RequestBody User user) {
        //将 user给userService中的register进行处理
        userService.register(user);
        return "success";
    }

    // 定义一个get类型的接口/users,用于查询所有用户
    @GetMapping("/users")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    // 定义一个get类型的接口，用于根据id查询对应用户
    // @PathVariable Long id 用于将url中{id}部分的id值转为Long类型然后赋值给id
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
package com.java_pratice.repository;

import com.java_pratice.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    // 根据邮箱查找用户
    @Query("SELECT * FROM users WHERE email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    // 根据邮箱和密码查找用户（用于登录验证）
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    Optional<User> findByEmailAndPassword(@Param("email") String email,
                                          @Param("password") String password);
}
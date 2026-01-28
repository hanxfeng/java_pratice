package com.example.java_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java_practice.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrdersMapper extends BaseMapper<Orders> {
    @Select("""
    select *
    from orders 
    where user_id = #{userId}
    """)
    List<Orders> selectOrdersByUserId (@Param("userId") Long userId);
}

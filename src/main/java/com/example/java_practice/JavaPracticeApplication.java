package com.example.java_practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.java_practice.mapper")
@SpringBootApplication
public class JavaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaPracticeApplication.class, args);
	}
}

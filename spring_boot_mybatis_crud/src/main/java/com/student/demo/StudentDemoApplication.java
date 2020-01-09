package com.student.demo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("com.student.demo.mapper")
public class StudentDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentDemoApplication.class, args);
    }
}

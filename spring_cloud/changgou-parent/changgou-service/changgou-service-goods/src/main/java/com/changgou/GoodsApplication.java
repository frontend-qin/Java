package com.changgou;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;


@EnableSwagger2Doc
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.changgou.dao")
public class GoodsApplication {
    public static void main(String[] args) {

        SpringApplication.run(GoodsApplication.class, args);
    }
}

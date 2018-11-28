package com.example.dubodemo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DubodemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DubodemoApplication.class, args);
    }
}

package com.example.dubodemo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableDubboConfiguration
public class DubodemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DubodemoApplication.class, args);
    }

    @RestController
    public class TestController {

        @GetMapping(value = "/hello")
        @SentinelResource("hello")
        public String hello() {
            return "Hello Sentinel";
        }
    }

}

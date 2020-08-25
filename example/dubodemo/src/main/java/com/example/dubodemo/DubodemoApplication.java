package com.example.dubodemo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.example.dubodemo.influxdb.InfluxDBDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

//import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@SpringBootApplication
@EnableScheduling
//@EnableDubboConfiguration
@NacosPropertySource(dataId = "bamboo.test", autoRefreshed = true)
public class DubodemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DubodemoApplication.class, args);
    }

    /*@Scheduled(fixedRate = 1000)
    public void doInsert(){
        Random random = new Random();
        InfluxDBDemo.insert(random.nextInt(1000));
    }*/

    @NacosValue(value = "${service.name:1}", autoRefreshed = true)
    private String serverName;

    @RestController
    public class TestController {

        @GetMapping(value = "/hello")
        @SentinelResource("hello")
        public String hello() {
            return "Hello Sentinel";
        }

        @GetMapping(value = "/test")
        @ResponseBody
        public String get() {
            return serverName;
        }
    }

}

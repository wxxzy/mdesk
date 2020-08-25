package com.example.dubodemo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
//@EnableDubboConfiguration
//@NacosPropertySource(dataId = "bamboo.test", autoRefreshed = true)
public class DubodemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DubodemoApplication.class, args);
    }

    /*@Scheduled(fixedRate = 1000)
    public void doInsert(){
        Random random = new Random();
        InfluxDBDemo.insert(random.nextInt(1000));
    }*/

    @RestController
    @RefreshScope
    public class TestController {

        @GetMapping(value = "/hello")
        @SentinelResource("hello")
        public String hello() {
            return "Hello Sentinel";
        }

        @Value("${service.name}")
        private String serverName;

        @GetMapping(value = "/test")
        @ResponseBody
        public String get() {
            return serverName;
        }
    }

}

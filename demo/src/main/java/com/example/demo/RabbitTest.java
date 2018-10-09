package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;

    @RequestMapping("/hello")
    public void hello() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    helloSender1.send();
                }
            }
        }.start();
        //while (true) {
            helloSender1.send();
        //}
    }
}

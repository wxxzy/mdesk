package com.example.adminserver;

import com.example.adminserver.service.IcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Autowired
    private IcdService icdService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("加载缓存......");
        String[] sourceArgs = args.getSourceArgs();
        for (String arg : sourceArgs) {
            System.out.print(arg + " ");
        }
        System.out.println();
        icdService.findAll();
        icdService.findIcdByLevel("3");
        icdService.findIcdByLevel("4");
        icdService.findIcdByLevel("6");

        icdService.findIcdByLevelStr("3");
        icdService.findIcdByLevelStr("4");
        icdService.findIcdByLevelStr("6");
    }
}

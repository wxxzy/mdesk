package com.thrift.service;

import org.apache.thrift.TException;

public class HelloServiceImpl implements HelloService.Iface {
    public String sayHello(User user) throws TException {
        return "hello,"+user.getName()+user.getEmail();
    }
}

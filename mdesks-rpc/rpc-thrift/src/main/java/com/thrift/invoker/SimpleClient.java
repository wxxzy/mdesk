package com.thrift.invoker;

import com.thrift.service.HelloService;
import com.thrift.service.User;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * 客户端调用
 */
public class SimpleClient {
    public void startClient(){
        String ip ="127.0.0.1";
        int port = 8091;
        int tomeout=1000;

        //1.创建transport
        TTransport tTransport=new TSocket(ip,port,tomeout);
        //2.创建protocol
        TProtocol protocol=new TBinaryProtocol(tTransport);
        //3.创建client
        HelloService.Client client=new HelloService.Client(protocol);
        try {
            //4.连接服务
            tTransport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName("world");
        user.setEmail("!");
        try {
            //5.调用服务
            String content = client.sayHello(user);
            System.out.println(content);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SimpleClient c = new SimpleClient();
        c.startClient();
    }

}

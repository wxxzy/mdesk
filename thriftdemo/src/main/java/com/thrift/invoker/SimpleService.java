package com.thrift.invoker;

import com.thrift.service.HelloService;
import com.thrift.service.HelloServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 启动服务
 */
public class SimpleService {
    public void startService() throws TTransportException{
        //1.创建processor
        TProcessor tProcessor=new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
        int port =8091;
        //2.创建transport阻塞通信
        TServerSocket serverSocket = new TServerSocket(port);
        //3.创建protocol
        TBinaryProtocol.Factory protocol  = new TBinaryProtocol.Factory();
        //4.设置服务器参数,processor,transport,protocol
        TServer.Args args = new TServer.Args(serverSocket);
        args.processor(tProcessor);
        args.protocolFactory(protocol);

        //5.开启服务
        TServer server = new TSimpleServer(args);

        server.serve();
    }

    public static void main(String[] args){
        SimpleService s = new SimpleService();
        try {
            s.startService();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}

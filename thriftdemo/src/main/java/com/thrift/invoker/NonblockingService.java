package com.thrift.invoker;

import com.thrift.service.HelloService;
import com.thrift.service.HelloServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * 非阻塞I/O服务
 */
public class NonblockingService {
    public void startService(){
        TProcessor tProcessor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
        int port = 8092;
        try {
            //创建非阻塞transport
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
            TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();

            //非阻塞需要这种传输方式
            TFramedTransport.Factory transport = new TFramedTransport.Factory();
            TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport);
            args.processor(tProcessor);
            args.protocolFactory(protocol);
            args.transportFactory(transport);

            //创建服务,类型nonblocking
            TServer server = new TNonblockingServer(args);

            server.serve();

        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        NonblockingService s = new NonblockingService();

        s.startService();

    }
}

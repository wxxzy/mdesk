package com.thrift.invoker;

import com.thrift.service.HelloService;
import com.thrift.service.User;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class NonblockClient {
    public void startClient(){
        String ip ="127.0.0.1";
        int port = 8092;
        int tomeout=1000;

        TAsyncClientManager clientManager;

        {
            try {
                clientManager = new TAsyncClientManager();
                TNonblockingTransport transport = new TNonblockingSocket(ip,port,tomeout);
                TProtocolFactory tProtocolFactory = new TCompactProtocol.Factory();
                HelloService.AsyncClient asyncClient = new HelloService.AsyncClient(tProtocolFactory,clientManager,transport);


                User user = new User();
                user.setName("world");
                user.setEmail("!");

                CountDownLatch latch = new CountDownLatch(1);

                AsynInvokerCallBack callBack = new AsynInvokerCallBack(latch);
                try {
                    asyncClient.sayHello(user,callBack);
                    try {
                        latch.await(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (TException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        NonblockClient client = new NonblockClient();
        client.startClient();
    }

}

class AsynInvokerCallBack implements AsyncMethodCallback<HelloService.AsyncClient.sayHello_call>{
    private CountDownLatch latch;

    public AsynInvokerCallBack(CountDownLatch latch){
        this.latch=latch;
    }

    public void onComplete(HelloService.AsyncClient.sayHello_call response) {
        try {
            System.out.println("response:"+response.getResult());
        } catch (TException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }

    }

    public void onError(Exception e) {
        latch.countDown();
    }
}

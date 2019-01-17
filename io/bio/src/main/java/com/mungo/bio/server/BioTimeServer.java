package com.mungo.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/17 14:22
 */
public class BioTimeServer {
    public static void main(String[] args) {
        int port = 9000;
        if(args != null &&args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }
        ServerSocket server = null;
        BioTimeServerHandlerExecutePool executePool = new BioTimeServerHandlerExecutePool(50,1000);
        try {
            server = new ServerSocket(port);
            System.out.println("server in port:"+port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                //接收请求并启动新线程处理
                //new Thread(new BioTimeServerHandler(socket)).start();
                //线程池处理
                executePool.execute(new BioTimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server !=null){
                System.out.println("server close");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server = null;
            }
        }

    }
}

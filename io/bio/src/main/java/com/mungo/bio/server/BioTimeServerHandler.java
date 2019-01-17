package com.mungo.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/17 14:30
 */
public class BioTimeServerHandler implements Runnable {
    private Socket socket;

    public BioTimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while (true){
                //接受客户端指令
                body = in.readLine();
                if(body == null){
                    break;
                }
                System.out.println("receive order: "+body);
                //响应指令并返回
                currentTime ="time order".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString():
                        "bady order";
                out.println(currentTime);
            }
        } catch (IOException e) {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if(out!=null){
                    out.close();
                    out=null;
                }
                if(socket!=null){
                    try {
                        this.socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    socket = null;
                }
            }
            e.printStackTrace();
        }
    }
}

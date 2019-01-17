package com.mungo.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/17 14:59
 */
public class BioTimeClient {
    public static void main(String[] args) {
        int port = 9000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        //while (true) {
            try {
                socket = new Socket("127.0.0.1", port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                //发送指令
                out.println("time order");
                System.out.println("send order 2 server succeed");
                //接受服务器返回结果
                String resp = in.readLine();
                System.out.println("Now is : " + resp);
            } catch (Exception e) {

            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if (out != null) {
                        out.close();
                        out = null;
                    }
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        socket = null;
                    }
                }
            }
        //}
    }
}

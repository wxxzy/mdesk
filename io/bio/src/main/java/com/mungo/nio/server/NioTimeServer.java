package com.mungo.nio.server;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/17 16:20
 */
public class NioTimeServer {

    public static void main(String[] args) {
        int port = 9001;
        if(args != null &&args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"nio-timeServer-01").start();

    }
}

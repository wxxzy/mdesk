package com.mungo.aio.server;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/18 11:06
 */
public class AioTimeServer {
    public static void main(String[] args) {
        int port = 9002;
        if(args != null &&args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        AioTimeServerHandler timeServer = new AioTimeServerHandler(port);
        new Thread(timeServer,"AioTimeServer").start();
    }
}

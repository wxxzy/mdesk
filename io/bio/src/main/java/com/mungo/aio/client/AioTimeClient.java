package com.mungo.aio.client;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/18 14:00
 */
public class AioTimeClient {
    public static void main(String[] args) {
        int port = 9002;
        if(args != null &&args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        new Thread(new AioTimeClientHandler("127.0.0.1",port)).start();
    }
}

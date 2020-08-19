package com.mungo.nio.client;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/17 16:21
 */
public class NioTimeClient {
    public static void main(String[] args) {
        int port = 9001;
        if(args != null &&args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        new Thread(new NioTimeClientHandler("127.0.0.1",port)).start();

    }
}

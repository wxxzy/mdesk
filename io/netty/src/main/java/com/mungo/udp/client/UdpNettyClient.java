package com.mungo.udp.client;

import com.mungo.udp.server.UdpNettyServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/30 18:27
 */
public class UdpNettyClient {
    public static void main(String[] args) {
        int port = 9004;
        if(args != null &&args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }

        new UdpNettyClient().run(port);
    }

    private void run(int port) {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
                .handler(new UdpNettyClientHandler());
        try {
            Channel ch = b.bind(0).sync().channel();
            //广播
            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("query",UTF_8),
                    new InetSocketAddress("255.255.255.255",port))).sync();
            if(!ch.closeFuture().await(15000)){
                System.out.println("查询超时");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}

package com.mungo.timeserver.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/18 15:48
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new LineBasedFrameDecoder(1024));
        //p.addLast(new StringDecoder());
        //p.addLast(new StringEncoder());
        p.addLast(new TimeServerHandler());
    }
}

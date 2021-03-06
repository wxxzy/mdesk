package com.mungo.udp.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.util.concurrent.ThreadLocalRandom;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/30 18:16
 */
public class UdpNettyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final String[] DICT = {"有福同享，有难同当" ,
            "邻居好，赛金宝" ,
            "远亲不如近邻，近邻不抵对门" ,
            "老乡见老乡，两眼泪汪汪" ,
            "在家靠父母，出门靠朋友" ,
            "交人交心，浇花浇根" ,
            "岁寒知松柏，患难见交情" ,
            "路遥知马力，日久见人心" ,
            "酒逢知己千杯少，话不投机半句多" ,
            "有缘千里来相会，无缘对面不相识" ,
            "多个朋友多条路，多个冤家多堵墙" ,
            "宁喝朋友的白水，不吃敌人的蜂蜜"};
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String req = msg.content().toString(UTF_8);
        System.out.println(req);
        if("query".equals(req)){
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("结果："+nextQuote(),UTF_8),msg.sender()));
        }
    }

    private String nextQuote() {
        int quoteId = ThreadLocalRandom.current().nextInt(DICT.length);
        return DICT[quoteId];
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

package com.mungo.file.server;

import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/31 16:21
 */
public class FileServerHandler  extends SimpleChannelInboundHandler<String> {
    private static final String CR = System.getProperty("line.separator");
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        File file = new File(msg);
        if(file.exists()){
            if(!file.isFile()) {
                ctx.writeAndFlush("Not a file: " + file + CR);
                return;
            }
            ctx.write(file + " " + file.length() + CR);
            RandomAccessFile accessFile = new RandomAccessFile(msg,"r");
            FileRegion fileRegion = new DefaultFileRegion(accessFile.getChannel(),0,accessFile.length());
            ctx.write(fileRegion);
            ctx.writeAndFlush(CR);
            accessFile.close();
        }else {
            ctx.writeAndFlush("Not a file: " + file + CR);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

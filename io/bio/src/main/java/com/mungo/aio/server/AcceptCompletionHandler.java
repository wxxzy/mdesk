package com.mungo.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/18 11:28
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioTimeServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel result, AioTimeServerHandler attachment) {
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        result.read(byteBuffer,byteBuffer,new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}

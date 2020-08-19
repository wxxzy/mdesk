package com.mungo.selfprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

import java.util.List;
import java.util.Map;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/2/11 11:17
 */
public class SelfMessageEncoder extends MessageToMessageEncoder<SelfMessage> {
    SelfMarshallingEncoder marshallingEncoder;

    public SelfMessageEncoder() {
        marshallingEncoder = new SelfMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, SelfMessage msg, List<Object> out) throws Exception {
        if(msg==null || msg.getHeader() == null){
            throw new Exception("The encode message is null");
        }
        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionId());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeInt(msg.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param :
                msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
        }
    }
}

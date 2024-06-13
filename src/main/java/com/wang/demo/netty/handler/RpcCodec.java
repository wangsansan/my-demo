package com.wang.demo.netty.handler;

import com.wang.demo.netty.web.RpcMessage;
import com.wang.demo.netty.web.request.RpcRequest;
import com.wang.demo.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/12 10:28
 */
@ChannelHandler.Sharable
public class RpcCodec extends MessageToMessageCodec<ByteBuf, RpcMessage> {

    // 魔数，版本，消息类型，序列号，序列化方式，长度，数据
    // 4，  2，   1，     4,    1,         4
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcMessage msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeInt(RpcMessage.MAGIC_NUM);
        buffer.writeShort(RpcMessage.VERSION);
        buffer.writeByte(msg.messageType());
        buffer.writeInt(msg.sequenceId());
        buffer.writeByte(RpcMessage.SERIALIZE_TYPE);
        String s = JsonUtils.writeToString(msg);
        byte[] bytes = s.getBytes(Charset.defaultCharset());
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
        out.add(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int magicNum = msg.readInt();
        int version = msg.readShort();
        byte classType = msg.readByte();
        int sequenceId = msg.readInt();
        Class<? extends RpcMessage> messageClass = RpcMessage.classMap.get(classType);
        int serializeType = msg.readByte();
        if (serializeType == RpcMessage.SERIALIZE_TYPE) {
            int length = msg.readInt();
            byte[] bytes = new byte[length];
            msg.readBytes(bytes);
            String s = new String(bytes);
            RpcMessage message = JsonUtils.parse(s, messageClass);
            out.add(message);
        }
    }

    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                new RpcCodec()
        );
        RpcRequest request = new RpcRequest();
        request.setClassName("className");
        request.setMethodName("hello");
        request.setParameterTypes(new Class[]{String.class});
        request.setReturnType(String.class);
        request.setArgs(new Object[]{"wang"});
        request.setSequenceId(1111);
        channel.writeOutbound(request);
    }


}

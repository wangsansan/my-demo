package com.wang.demo.netty.handler;

import com.wang.demo.netty.web.response.RpcResponse;
import com.wang.demo.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 21:56
 */
public class RpcResponseHandler extends SimpleChannelInboundHandler<RpcResponse> {

    public static Map<Integer, CompletableFuture> futureMap = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        int sequenceId = msg.getSequenceId();
        CompletableFuture future = futureMap.remove(sequenceId);
        if (Objects.nonNull(future)) {
            if (msg.getCode() == 0) {
                future.complete(msg.getResult());
                System.out.println("====result:" + JsonUtils.writeToString(msg.getResult()));
            } else {
                future.completeExceptionally(msg.getCause());
            }
        }
    }
}

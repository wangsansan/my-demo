package com.wang.demo.netty.handler;

import com.wang.demo.netty.service.ServiceFactory;
import com.wang.demo.netty.web.request.RpcRequest;
import com.wang.demo.netty.web.response.RpcResponse;
import com.wang.demo.utils.JsonUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 21:45
 */
@ChannelHandler.Sharable
public class RpcRequestHandler extends SimpleChannelInboundHandler<RpcRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        String className = msg.getClassName();
        Class<?> aClass = Class.forName(className);
        Object instance = ServiceFactory.findInstance(aClass);
        if (Objects.nonNull(instance)) {
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.setSequenceId(msg.getSequenceId());
            try {
                Method method = aClass.getMethod(msg.getMethodName(), msg.getParameterTypes());
                Object returnValue = method.invoke(instance, msg.getArgs());
                rpcResponse.setCode(0);
                rpcResponse.setResult(returnValue);
            } catch (Exception e) {
                rpcResponse.setCode(-1);
                rpcResponse.setCause(e);
            }
            ctx.writeAndFlush(rpcResponse);
        }
    }
}

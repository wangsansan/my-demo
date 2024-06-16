package com.wang.demo.netty;

import com.wang.demo.netty.client.HelloService;
import com.wang.demo.netty.handler.MessageFrameHandler;
import com.wang.demo.netty.handler.RpcCodec;
import com.wang.demo.netty.handler.RpcResponseHandler;
import com.wang.demo.netty.register.ServiceAddress;
import com.wang.demo.netty.web.request.RpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/11 18:00
 */
@Slf4j
public class RpcClient {

    public static Map<ServiceAddress, Channel> channelMap = new ConcurrentHashMap<>();

    private static LoggingHandler loggingHandler = new LoggingHandler();

    public static final RpcResponseHandler rpcResponseHandler = new RpcResponseHandler();

    public static final RpcCodec RPC_CODEC = new RpcCodec();

    public static Channel getChannel(ServiceAddress serviceAddress) {
        if (channelMap.get(serviceAddress) != null) {
            return channelMap.get(serviceAddress);
        }
        synchronized (RpcClient.class) {
            if (channelMap.get(serviceAddress) == null) {
                Channel channel = initChannel(serviceAddress.getHost(), serviceAddress.getPort());
                channelMap.put(serviceAddress, channel);
            }
            return channelMap.get(serviceAddress);
        }
    }

    public static Channel initChannel(String host, Integer port) {
        String connectHost = StringUtils.defaultString(host, "localhost");
        int connectPort = Objects.isNull(port) ? 8084 : port;
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture channelFuture = bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MessageFrameHandler());
                        ch.pipeline().addLast(loggingHandler);
                        ch.pipeline().addLast(RPC_CODEC);
                        ch.pipeline().addLast(rpcResponseHandler);
                        ch.pipeline().addLast(loggingHandler);
                    }
                })
                .connect(connectHost, connectPort);
        try {
            channelFuture.sync();
            Channel channel = channelFuture.channel();
            channel.closeFuture().addListener((future -> {
                workerGroup.shutdownGracefully();
            }));
            return channel;
        } catch (Exception e) {
            workerGroup.shutdownGracefully();
        }
        return null;
    }

    public static void main(String[] args) {
        RpcRequest request = new RpcRequest();
        request.setSequenceId(1);
        request.setClassName(HelloService.class.getName());
        request.setMethodName("sayHello");
        request.setReturnType(String.class);
        request.setArgs(new Object[]{"wang"});
        request.setParameterTypes(new Class[]{String.class});
        getChannel(new ServiceAddress("localhost", 8084)).writeAndFlush(request);
    }

}

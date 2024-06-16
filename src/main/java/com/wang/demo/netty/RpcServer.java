package com.wang.demo.netty;

import com.wang.demo.netty.handler.MessageFrameHandler;
import com.wang.demo.netty.handler.RpcCodec;
import com.wang.demo.netty.handler.RpcRequestHandler;
import com.wang.demo.netty.register.ServiceRegistryManager;
import com.wang.demo.netty.service.ServiceFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/11 17:35
 */
@Slf4j
public class RpcServer {

    private static LoggingHandler loggingHandler = new LoggingHandler();

    public static final RpcRequestHandler rpcRequestHandler = new RpcRequestHandler();

    public static final RpcCodec RPC_CODEC = new RpcCodec();

    public void startServer() {
        String host = "localhost";
        int port = 8084;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> startServer(host, port));
    }

    public void startServer(String host, int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new MessageFrameHandler())
                                .addLast("before log", loggingHandler)
                                .addLast("rpc codec", RPC_CODEC)
                                .addLast("request handler", rpcRequestHandler)
                                .addLast("after log", loggingHandler);
                    }
                })
                .bind(host, port);
        try {
            channelFuture.sync();
            log.info("server connected");
            // 注册服务
            ServiceRegistryManager.registerService(host, port, ServiceFactory.listService());
            log.info("service registered");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("服务端出错", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            ServiceRegistryManager.unregisterService(host, port, ServiceFactory.listService());
        }
    }

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.startServer();
    }

}

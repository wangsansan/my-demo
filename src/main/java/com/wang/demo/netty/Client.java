package com.wang.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/11 18:00
 */
@Slf4j
public class Client {

    public static Channel channel = null;

    private static LoggingHandler loggingHandler = new LoggingHandler();

    public static Channel getChannel() {
        if (Objects.nonNull(channel)) {
            return channel;
        }
        synchronized (Client.class) {
            if (Objects.isNull(channel)) {
                initChannel();
            }
            return channel;
        }
    }

    public static void initChannel() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture channelFuture = bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(loggingHandler);
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ByteBuf buf = ctx.alloc().buffer();
                                buf.writeBytes("hello".getBytes());
                                ctx.writeAndFlush(buf);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                log.info("server receive:[{}]", byteBuf.toString(Charset.defaultCharset()));
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                ctx.channel().close();
                            }

                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                ctx.channel().close();
                            }
                        });
                        ch.pipeline().addLast(loggingHandler);
                    }
                })
                .connect("localhost", 8084);
        try {
            channelFuture.sync();
            channel = channelFuture.channel();
            channel.closeFuture().addListener((future -> {
                workerGroup.shutdownGracefully();
            }));
        } catch (Exception e) {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {


    }

}

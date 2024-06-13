package com.wang.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/11 17:35
 */
@Slf4j
public class Server {

    private static LoggingHandler loggingHandler = new LoggingHandler();

    public static final StringDecoder STRING_DECODER = new StringDecoder();

    public static final StringEncoder STRING_ENCODER = new StringEncoder();

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("before log", loggingHandler)
                                .addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("========channelRead========");
                                        ByteBuf byteBuf = (ByteBuf) msg;
                                        log.info("server receive:[{}]", byteBuf.toString(Charset.defaultCharset()));
                                        ByteBuf buf = ctx.alloc().buffer();
                                        buf.writeBytes("hello, too!".getBytes());
                                        ctx.writeAndFlush(buf);
                                    }
                                })
                                .addLast("after log", loggingHandler);
                    }
                })
                .bind(8084);
        try {
            channelFuture.sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}

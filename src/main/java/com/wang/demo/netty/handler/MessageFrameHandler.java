package com.wang.demo.netty.handler;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 22:20
 */
public class MessageFrameHandler extends LengthFieldBasedFrameDecoder {

    public MessageFrameHandler() {
        super(1024, 12, 4, 0, 0);
    }
}

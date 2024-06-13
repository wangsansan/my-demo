package com.wang.demo.netty.session;

import com.google.common.collect.Maps;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/11 20:24
 */
public class SessionService {

    public static final Map<String, NioSocketChannel> USER_CHANNEL_MAP = Maps.newHashMap();

    public static final Map<NioSocketChannel,String> CHANNEL_USER_MAP = Maps.newHashMap();

    public void bind(String userName, NioSocketChannel channel) {
        USER_CHANNEL_MAP.put(userName, channel);
        CHANNEL_USER_MAP.put(channel, userName);
    }

}

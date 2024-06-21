package com.wang.demo.netty.invoke;

import com.wang.demo.netty.RpcClient;
import com.wang.demo.netty.register.ServiceAddress;
import io.netty.channel.Channel;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/21 08:22
 */
public interface ChannelUseStrategy {

    default Channel digChannel(ServiceAddress serviceAddress, ChannelPool channelPool) {
        Channel[] channels = channelPool.getChannels();
        int index = computeIndex(serviceAddress);
        int realIndex = index % channels.length;
        if (channels[realIndex] != null && channels[realIndex].isActive()) {
            return channels[realIndex];
        }
        if (channels[realIndex] != null) {
            channels[realIndex].close();
        }
        channels[realIndex] = null;
        synchronized (channelPool.getLocks()[realIndex]) {
            Channel channel = RpcClient.initChannel(serviceAddress.getHost(), serviceAddress.getPort());
            channels[realIndex] = channel;
            return channel;
        }
    }

    int computeIndex(ServiceAddress serviceAddress);
}

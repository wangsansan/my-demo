package com.wang.demo.netty.invoke;

import com.wang.demo.netty.RpcClient;
import com.wang.demo.netty.register.ServiceAddress;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/17 07:13
 * 处理连接池，利用lru进行连接池的汰换
 */
public class RpcClientManager {

    private static LRU<ServiceAddress, ChannelPool> channelCache = new LRU(5, 10, 0.75f, true);

    public static ChannelUseStrategy channelUseStrategy;

    private static ChannelUseStrategy defaultChannelUseStrategy = new RobinStrategy();

    public static Channel getChannel(ServiceAddress serviceAddress, int poolSize) {
        channelCache.putIfAbsent(serviceAddress, new ChannelPool(poolSize));
        ChannelPool channelPool = channelCache.get(serviceAddress);
        return digChannel(serviceAddress, channelPool);
    }

    /**
     * 默认用轮询的方式来进行连接池的选择
     * @param channelPool
     * @return
     */
    private static Channel digChannel(ServiceAddress serviceAddress, ChannelPool channelPool) {
        ChannelUseStrategy useStrategy = channelUseStrategy;
        if (Objects.isNull(useStrategy)) {
            useStrategy = defaultChannelUseStrategy;
        }
        return useStrategy.digChannel(serviceAddress, channelPool);
    }

}

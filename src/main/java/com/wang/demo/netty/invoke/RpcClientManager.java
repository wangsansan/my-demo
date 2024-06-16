package com.wang.demo.netty.invoke;

import com.wang.demo.netty.RpcClient;
import com.wang.demo.netty.register.ServiceAddress;
import io.netty.channel.Channel;


/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/17 07:13
 * 处理连接池，利用lru进行连接池的汰换
 */
public class RpcClientManager {

    private static LRU<ServiceAddress, Channel> channelCache = new LRU(5, 10, 0.75f, true);

    public static Channel getChannel(ServiceAddress serviceAddress) {
        if (channelCache.get(serviceAddress) != null) {
            return channelCache.get(serviceAddress);
        }
        synchronized (RpcClientManager.class) {
            if (channelCache.get(serviceAddress) == null) {
                Channel channel = RpcClient.initChannel(serviceAddress.getHost(), serviceAddress.getPort());
                channelCache.put(serviceAddress, channel);
            }
            return channelCache.get(serviceAddress);
        }
    }

}

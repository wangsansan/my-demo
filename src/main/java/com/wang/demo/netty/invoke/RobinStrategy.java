package com.wang.demo.netty.invoke;

import com.wang.demo.netty.register.ServiceAddress;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/21 08:23
 */
public class RobinStrategy implements ChannelUseStrategy {

    private static Map<ServiceAddress, AtomicInteger> channelIndex = new ConcurrentHashMap<>();

    @Override
    public int computeIndex(ServiceAddress serviceAddress) {
        // 线程安全
        channelIndex.putIfAbsent(serviceAddress, new AtomicInteger(0));
        AtomicInteger indexHelper = channelIndex.get(serviceAddress);
        // 线程安全
        return indexHelper.getAndIncrement();
    }
}

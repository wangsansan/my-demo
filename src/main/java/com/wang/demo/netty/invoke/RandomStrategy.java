package com.wang.demo.netty.invoke;

import com.wang.demo.netty.register.ServiceAddress;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/21 08:27
 */
public class RandomStrategy implements ChannelUseStrategy {

    private Random random = new SecureRandom();

    @Override
    public int computeIndex(ServiceAddress serviceAddress) {
        return Math.abs(random.nextInt());
    }
}

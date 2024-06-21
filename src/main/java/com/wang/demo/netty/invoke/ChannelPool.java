package com.wang.demo.netty.invoke;

import io.netty.channel.Channel;
import lombok.Data;


/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/21 07:58
 */
@Data
public class ChannelPool {

    private int poolSize;

    private Channel[] channels;

    private Object[] locks;

    public ChannelPool(int poolSize) {
        this.poolSize = poolSize;
        channels = new Channel[poolSize];
        locks = new Object[poolSize];
        for (int i = 0; i < poolSize; i++) {
            locks[i] = new Object();
        }
    }

}

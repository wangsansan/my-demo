package com.wang.demo.threadPool;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/23 09:00
 */
@FunctionalInterface
public interface RejectPolicy {

    void apply(Runnable t);

}

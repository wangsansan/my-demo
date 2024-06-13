package com.wang.demo.netty.service;

import com.wang.demo.netty.client.HelloService;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 10:14
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return name + ", hello!";
    }

}

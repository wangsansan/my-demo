package com.wang.demo.netty.service;

import com.wang.demo.netty.client.HelloService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 21:47
 */
public class ServiceFactory {

    private static Map<Class, Object> instanceMap = new HashMap<>();

    static {
        instanceMap.put(HelloService.class, new HelloServiceImpl());
    }

    public static  <T> T findInstance(Class<T> clazz) {
        Object o = instanceMap.get(clazz);
        if (Objects.isNull(o)) {
            return null;
        }
        return (T) o;
    }


}

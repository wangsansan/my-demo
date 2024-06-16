package com.wang.demo.netty.service;

import com.google.common.collect.Lists;
import com.wang.demo.netty.client.HelloService;
import com.wang.demo.netty.register.ServiceRegistryManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 21:47
 */
public class ServiceFactory {

    private static Map<Class, Object> instanceMap = new HashMap<>();

    /**
     * 后期改成Spring来管理
     */
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

    public static List<String> listService() {
        List<String> result = Lists.newArrayListWithExpectedSize(instanceMap.keySet().size());
        for (Class clazz : instanceMap.keySet()) {
            result.add(clazz.getName());
        }
        return result;
    }


}

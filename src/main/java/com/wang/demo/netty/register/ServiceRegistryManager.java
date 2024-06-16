package com.wang.demo.netty.register;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/16 17:00
 * 这个服务是模仿注册中心的，以后看看能不能用Redis或者zookeeper来替代
 */
public class ServiceRegistryManager {

    private static ServiceRegistry serviceRegistry = new LocalServiceRegistry();

    /**
     * 第一版简单写，先不支持定时同步、接口版本功能
     * @param host
     * @param port
     * @param serviceKey
     */
    public static void registerService(String host, int port, String serviceKey) {
        serviceRegistry.registerService(host, port, Collections.singletonList(serviceKey));
    }

    public static void registerService(String host, int port, List<String> serviceKeyList) {
        serviceRegistry.registerService(host, port, serviceKeyList);
    }

    public static void unregisterService(String host, int port, List<String> serviceKeyList) {
        serviceRegistry.unregisterService(serviceKeyList, host, port);
    }

    public static List<ServiceAddress> findServiceAddress(String serviceKey) {
        return serviceRegistry.findServiceAddress(serviceKey);
    }

}

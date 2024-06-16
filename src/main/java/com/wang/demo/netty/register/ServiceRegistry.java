package com.wang.demo.netty.register;

import java.util.List;
import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/16 16:47
 */
public interface ServiceRegistry {

    void registerService(String host, int port, List<String> serviceKeyList);

    void unregisterService(List<String> serviceKeyList, String host, int port);

    List<ServiceAddress> findServiceAddress(String serviceKey);

}

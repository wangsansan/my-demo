package com.wang.demo.netty.invoke;

import com.wang.demo.netty.Client;
import com.wang.demo.netty.RpcClient;
import com.wang.demo.netty.RpcServer;
import com.wang.demo.netty.client.HelloService;
import com.wang.demo.netty.handler.RpcResponseHandler;
import com.wang.demo.netty.register.ServiceAddress;
import com.wang.demo.netty.register.ServiceRegistryManager;
import com.wang.demo.netty.web.request.RpcRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 10:16
 */
@Slf4j
public class Application {

    public static final AtomicInteger seq = new AtomicInteger(1);

    @SneakyThrows
    public static void main(String[] args) {
        HelloService helloService = getProxyInstance(HelloService.class);
        if (Objects.isNull(helloService)) {
            log.error("oops");
        }
        System.out.println("=====result=====" + helloService.sayHello("chun"));
        helloService.sayHello("sheng");
    }

    public static <T> T getProxyInstance(Class<T> serviceClass) {
        return getProxyInstance(serviceClass, -1);
    }


    public static <T> T getProxyInstance(Class<T> serviceClass, int timeOut) {
        String serviceKey = serviceClass.getName();
        List<ServiceAddress> serviceAddressList = ServiceRegistryManager.findServiceAddress(serviceKey);
        if (CollectionUtils.isEmpty(serviceAddressList)) {
            return null;
        }
        // TODO: 2024/6/16 实现负载均衡
        ServiceAddress serviceAddress = serviceAddressList.get(0);
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                (proxy1, method, arg) -> {
                    RpcRequest request = new RpcRequest();
                    request.setSequenceId(seq.getAndDecrement());
                    request.setClassName(HelloService.class.getName());
                    request.setMethodName(method.getName());
                    request.setReturnType(method.getReturnType());
                    request.setArgs(arg);
                    request.setParameterTypes(method.getParameterTypes());
                    CompletableFuture<Object> future = new CompletableFuture<>();
                    RpcResponseHandler.futureMap.put(request.getSequenceId(), future);
                    RpcClient.getChannel(serviceAddress).writeAndFlush(request);

                    Object o = null;
                    if (timeOut <= 0) {
                        o = future.get();
                    } else {
                        o = future.get(timeOut, TimeUnit.MILLISECONDS);
                    }
                    return o;
                });
    }

}

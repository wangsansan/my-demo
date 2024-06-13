package com.wang.demo.netty.invoke;

import com.wang.demo.netty.Client;
import com.wang.demo.netty.RpcClient;
import com.wang.demo.netty.client.HelloService;
import com.wang.demo.netty.handler.RpcResponseHandler;
import com.wang.demo.netty.web.request.RpcRequest;

import java.lang.reflect.Proxy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 10:16
 */
public class Application {

    public static final AtomicInteger seq = new AtomicInteger(1);

    public static void main(String[] args) {
        HelloService helloService = getProxyInstance(HelloService.class);
        System.out.println("=====result=====" + helloService.sayHello("chun"));
    }

    public static <T> T getProxyInstance(Class<T> serviceClass) {
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
                    RpcClient.getChannel().writeAndFlush(request);
                    Object o = future.get();
                    return o;
                });
    }

}

package com.wang.demo.service.factoryBean.config;

import com.wang.demo.service.factoryBean.MyFactoryBean;
import com.wang.demo.service.factoryBean.MyProxy;
import com.wang.demo.service.factoryBean.MyRealService;
import com.wang.demo.service.factoryBean.ProxyFactoryBean;
import com.wang.demo.service.factoryBean.service.ProxyService1;
import com.wang.demo.service.factoryBean.service.ProxyService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/5 16:32
 */
@Configuration
public class ProxyConfig {

    @Bean
    @Lazy
    public MyProxy proxyService1() {
        System.out.println("init proxyService1");
        MyProxy proxy = new MyProxy();
        proxy.setProxyClass(ProxyService1.class);
        return proxy;
    }

    @Bean
    @Lazy
    // 此处如果不指定返回值泛型，照样会被其他实例注入时强制执行
    public MyFactoryBean<ProxyService2> proxyService2() {
        System.out.println("init proxyService2");
        MyFactoryBean<ProxyService2> proxy = new MyFactoryBean<>();
        proxy.setProxyClass(ProxyService2.class);
        return proxy;
    }

    @Bean
    @Lazy
    public ProxyFactoryBean proxyService3() {
        System.out.println("init proxyService3");
        return new ProxyFactoryBean();
    }

    @Bean
    @Lazy
    public MyRealService myRealService() {
        System.out.println("init myRealService");
        return new MyRealService();
    }

}

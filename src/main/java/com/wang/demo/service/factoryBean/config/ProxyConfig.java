package com.wang.demo.service.factoryBean.config;

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

    /**
     * 此处代码是个比较不合常理的实验
     * 可以看到 proxyService1 和 proxyService2
     * @return
     */

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
    public MyProxy proxyService2() {
        System.out.println("init proxyService2");
        MyProxy proxy = new MyProxy();
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

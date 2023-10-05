package com.wang.demo.service.factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/5 16:30
 */
public class MyProxy implements FactoryBean {

    private Class<?> proxyClass;

    public void setProxyClass(Class<?> proxyClass) {
        this.proxyClass = proxyClass;
    }

    @Override
    public Object getObject() throws Exception {
        return proxyClass.newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return proxyClass;
    }
}

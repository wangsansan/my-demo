package com.wang.demo.service.factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/9 07:52
 */
public class MyFactoryBean<T> implements FactoryBean<T> {

    private Class<T> proxyClass;

    public void setProxyClass(Class<T> proxyClass) {
        this.proxyClass = proxyClass;
    }

    @Override
    public T getObject() throws Exception {
        return proxyClass.newInstance();
    }

    @Override
    public Class<T> getObjectType() {
        return proxyClass;
    }
}

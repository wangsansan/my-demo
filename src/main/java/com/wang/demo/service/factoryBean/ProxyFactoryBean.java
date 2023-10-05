package com.wang.demo.service.factoryBean;

import com.wang.demo.service.factoryBean.service.ProxyService3;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/5 17:23
 */
public class ProxyFactoryBean implements FactoryBean<ProxyService3> {
    @Override
    public ProxyService3 getObject() throws Exception {
        return ProxyService3.class.newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return ProxyService3.class;
    }
}

package com.wang.demo.service.request.processor;

import com.wang.demo.service.request.MyRequest;
import com.wang.demo.service.request.MyRequestFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 6:35 下午
 */

@Component
public class MyRequestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        configurableListableBeanFactory.registerResolvableDependency(MyRequest.class, new MyRequestFactory());
    }
}

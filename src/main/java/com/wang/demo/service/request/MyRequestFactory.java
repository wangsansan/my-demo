package com.wang.demo.service.request;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

import java.io.Serializable;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 6:31 下午
 */
public class MyRequestFactory implements ObjectFactory<MyRequest>, Serializable {

    @Override
    public MyRequest getObject() throws BeansException {
        return new MyServletRequest();
    }

    @Override
    public String toString() {
        return "my request factory";
    }
}

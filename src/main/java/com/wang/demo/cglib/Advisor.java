package com.wang.demo.cglib;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/28 17:35
 */
public interface Advisor {

    void beforeMethodInvoke();

    void afterMethodInvoke();

}

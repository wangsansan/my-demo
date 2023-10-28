package com.wang.demo.cglib;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/28 17:38
 */
public class Advisor1 implements Advisor {
    @Override
    public void beforeMethodInvoke() {
        System.out.println("advisor1 before");
    }

    @Override
    public void afterMethodInvoke() {
        System.out.println("advisor1 after");
    }
}

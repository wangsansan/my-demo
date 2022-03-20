package com.wang.demo.spring;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/3/20 8:07 下午
 */
public class CycleService3 implements MyInterface3{

    private MyInterface4 cycleService4;

    @Override
    public void doSomething() {
        System.out.println("this is CycleService3:" + this.getClass() +"; my cycleService4 is:" + cycleService4.getClass());
    }
}

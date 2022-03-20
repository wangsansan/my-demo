package com.wang.demo.spring;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/3/20 8:07 下午
 */
public class CycleService4 implements MyInterface4{

    private MyInterface3 cycleService3;

    @Override
    public void doSomething() {
        System.out.println("this is CycleService4:" + this.getClass() +"; my cycleService3 is:" + cycleService3.getClass());
    }
}

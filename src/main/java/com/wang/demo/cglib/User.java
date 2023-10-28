package com.wang.demo.cglib;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/28 17:34
 */
public class User {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @TestAop("study")
    public void study() {
        System.out.println(name + " is studying");
    }

    @TestAop("eat")
    private void eat() {
        System.out.println(name + " is to eat");
    }

}

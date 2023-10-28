package com.wang.demo.cglib;

import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/28 17:34
 */
public class UserProxy extends User {

    private User target;

    private List<Advisor> advisors;

    public void setTarget(User target) {
        this.target = target;
    }

    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }

    @Override
    public void study() {
        advisors.forEach(Advisor::beforeMethodInvoke);
        target.study();
        advisors.forEach(Advisor::afterMethodInvoke);
    }

    private void eat() {
        advisors.forEach(Advisor::beforeMethodInvoke);
        target.study();
        advisors.forEach(Advisor::afterMethodInvoke);
    }

    public static User getProxy(User rawBean) {
        UserProxy userProxy = new UserProxy();
        userProxy.setTarget(rawBean);
        userProxy.setAdvisors(findAdvisors());
        return userProxy;
    }

    private static List<Advisor> findAdvisors() {
        return Collections.singletonList(new Advisor1());
    }

    public static void main(String[] args) {
        // create bean
        User user = new User();
        // populate bean
        user.setName("wang");
        // proxy bean
        User userBean = getProxy(user);
        ReflectionUtils.doWithMethods(User.class, method -> {
            try {
                TestAop annotation = method.getAnnotation(TestAop.class);
                System.out.println("run method:" + annotation.value());
                method.setAccessible(true);
                method.invoke(userBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, method -> method.isAnnotationPresent(TestAop.class));
    }
}

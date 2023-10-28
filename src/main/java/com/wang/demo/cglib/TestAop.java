package com.wang.demo.cglib;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: Wangchunsheng
 * @Date: 2023/10/28 17:45
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TestAop {

    String value() default "";

}

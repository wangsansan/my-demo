package com.wang.demo.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/25 8:31 下午
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcelField {

    String name() default "";

    String value() default "";

}

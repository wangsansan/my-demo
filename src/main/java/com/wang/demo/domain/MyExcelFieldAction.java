package com.wang.demo.domain;

import com.wang.demo.annotation.MyExcelField;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/25 8:34 下午
 */
public class MyExcelFieldAction {

    public static void main(String[] args) {
        List<MyExcelPojo> pojoList = new ArrayList<>();
        ReflectionUtils.doWithFields(MyDomain.class, (field)->{
            MyExcelField annotation = field.getAnnotation(MyExcelField.class);
            MyExcelPojo myExcelPojo = new MyExcelPojo();
            Field targetField = ReflectionUtils.findField(MyExcelPojo.class, annotation.name());
            ReflectionUtils.makeAccessible(targetField);
            ReflectionUtils.setField(targetField, myExcelPojo, Long.valueOf(annotation.value()));
            pojoList.add(myExcelPojo);
        }, field -> field.isAnnotationPresent(MyExcelField.class));
        System.out.println(pojoList);
    }



}

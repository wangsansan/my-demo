package com.wang.demo.spring;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/3/8 3:56 下午
 */
@NoArgsConstructor
@Slf4j
@ToString
@Data
public class MetaNode {

    private Class clazz;

    private Map<String, Field> fieldMap;

    private List<Class> interfaces;

}

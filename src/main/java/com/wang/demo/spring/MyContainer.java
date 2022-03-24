package com.wang.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/3/8 7:22 下午
 */
@Slf4j
public class MyContainer {

    /**
     * 保存所有需要处理的类信息：包括类以及他们需要设置的属性
     */
    private static Map<Class, MetaNode> META_NODE_MAP = new HashMap<>();

    /**
     * 保存最终生成的实例
     */
    private static Map<Class, Object> BEAN_MAP = new HashMap<>();

    public static void registerClass(Class clazz, List<Field> fieldList) {
        if (!META_NODE_MAP.containsKey(clazz)) {
            MetaNode metaNode = new MetaNode();
            metaNode.setClazz(clazz);
            Map<String, Field> fieldMap = CollectionUtils.emptyIfNull(fieldList).stream()
                    .collect(Collectors.toMap(Field::getName, Function.identity()));
            metaNode.setFieldMap(fieldMap);
            META_NODE_MAP.putIfAbsent(clazz, metaNode);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(BEAN_MAP.values());
            System.out.println("init==========");
            // 1. 注册需要处理的class
            registerClass();

            // 2. 遍历所有需要处理的class，然后进行 instance 和 inject
            for (Class clazz : MapUtils.emptyIfNull(META_NODE_MAP).keySet()) {
                if (BEAN_MAP.containsKey(clazz)) {
                    continue;
                }
                Object instance = complete(clazz);
                BEAN_MAP.putIfAbsent(clazz, instance);
            }

            MyInterface o = (MyInterface)BEAN_MAP.get(Node.class);
            o.doSomething();

            System.out.println(BEAN_MAP.values());
            System.out.println("done==========");
        } catch (Exception e) {
            log.error("Oops, there is a error", e);
        }

    }

    private static Object complete(Class clazz) throws Exception {
        MetaNode metaNode = META_NODE_MAP.get(clazz);
        if (Objects.isNull(metaNode)) {
            return null;
        }
        // 1. 实例化
        Object instance = instance(clazz);
        // 2. 属性设置
        injectField(instance, metaNode.getFieldMap());
        // 3. aop
        instance = wrapIfNecessary(clazz, instance);
        return instance;
    }

    private static void registerClass() throws Exception{
        registerClass(Node.class, Collections.singletonList(Node.class.getDeclaredField("item")));
        registerClass(Item.class, null);
    }

    private static Object instance(Class clazz) throws Exception{
        return clazz.newInstance();
    }

    private static void injectField(Object instance, Map<String, Field> fieldMap) throws Exception {
        for (Map.Entry<String, Field> fieldEntry : MapUtils.emptyIfNull(fieldMap).entrySet()) {
            Field field = fieldEntry.getValue();
            Class<?> type = field.getType();
            Object toInject = BEAN_MAP.get(type);
            if (Objects.isNull(toInject)) {
                toInject = complete(type);
                BEAN_MAP.putIfAbsent(type, toInject);
            }
            field.setAccessible(true);
            field.set(instance, toInject);
        }

    }

    private static Object wrapIfNecessary(Class clazz, Object object) {
        Class[] interfaces = clazz.getInterfaces();
        if (Objects.isNull(interfaces) || interfaces.length == 0) {
            return object;
        }

        Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("it is a proxy doing its job:" + method.getName());
                Object invoke = method.invoke(object, args);
                return invoke;
            }
        });

        return proxy;

    }

}

package com.wang.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/3/8 7:22 下午
 * 先proxy，后inject
 */
@Slf4j
public class MyContainer5 {

    /**
     * 保存所有需要处理的类信息：包括类以及他们需要设置的属性
     */
    private static Map<Class, MetaNode> META_NODE_MAP = new HashMap<>();

    /**
     * 保存最终生成的实例
     */
    private static Map<Class, Object> BEAN_MAP = new HashMap<>();

//    private static Map<Class, Object> NON_FIELD_BEAN_MAP = new HashMap<>();

    private static Map<Class, Object> EARLY_BEAN_MAP = new HashMap<>();

    private static Set<Class> CACHE_PROXYED = new HashSet<>();

    public static void registerClass(Class clazz, List<Field> fieldList, List<Class> interfaces) {
        if (!META_NODE_MAP.containsKey(clazz)) {
            MetaNode metaNode = new MetaNode();
            metaNode.setClazz(clazz);
            Map<String, Field> fieldMap = CollectionUtils.emptyIfNull(fieldList).stream()
                    .collect(Collectors.toMap(Field::getName, Function.identity()));
            metaNode.setFieldMap(fieldMap);
            metaNode.setInterfaces(ListUtils.emptyIfNull(interfaces));
            META_NODE_MAP.putIfAbsent(clazz, metaNode);
        }
    }

    public static Object getBean(Class clazz) {
        Object o = BEAN_MAP.get(clazz);
        if (Objects.nonNull(o)) {
            return o;
        }
        o = EARLY_BEAN_MAP.get(clazz);
        return o;
    }

    public static void main(String[] args) {
        try {
            System.out.println(BEAN_MAP.values());
            System.out.println("init==========");
            // 1. 注册需要处理的class
            registerClass();

            // 2. 遍历所有需要处理的class，然后进行 instance 和 inject
            for (Map.Entry<Class, MetaNode> entry: MapUtils.emptyIfNull(META_NODE_MAP).entrySet()) {
                if (BEAN_MAP.containsKey(entry.getKey())) {
                    continue;
                }
                Object instance = complete(entry.getKey());
                addToContext(entry.getKey(), instance);
            }


            System.out.println(BEAN_MAP.values());
            System.out.println("done==========");
        } catch (Exception e) {
            log.error("Oops, there is a error", e);
        }

    }

    private static void addToContext(Class clazz, Object instance) {
        Set<Class> clazzSet = new HashSet<>();
        clazzSet.add(clazz);
        if (!clazz.isInterface()) {
            clazzSet.addAll(Arrays.asList(clazz.getInterfaces()));
        }
        EARLY_BEAN_MAP.entrySet().removeIf(it -> it.getKey().equals(clazz));
        BEAN_MAP.putIfAbsent(clazz, instance);
    }

    private static Object complete(Class clazz) throws Exception {
        clazz = findRealInjectClass(clazz);
        MetaNode metaNode = META_NODE_MAP.get(clazz);
        if (Objects.isNull(metaNode)) {
            return null;
        }

        // 如果容器里已经有了对应的数据，那么就返回
        if (getBean(metaNode.getClazz()) != null) {
            return getBean(metaNode.getClazz());
        }

        // 1. 实例化
        Object instance = instance(metaNode.getClazz());
        // 2. aop
        Object proxyed = wrapIfNecessary(metaNode.getClazz(), instance);

        EARLY_BEAN_MAP.put(clazz, proxyed);

        if (MapUtils.isNotEmpty(metaNode.getFieldMap())) {
            // 3. 属性设置
            injectField(instance, metaNode.getFieldMap());
        }

        return proxyed;
    }

    private static void registerClass() throws Exception{
        registerClass(CycleService3.class, Collections.singletonList(CycleService3.class.getDeclaredField("cycleService4")), Collections.singletonList(MyInterface3.class));
        registerClass(CycleService4.class, Collections.singletonList(CycleService4.class.getDeclaredField("cycleService3")), Collections.singletonList(MyInterface4.class));
    }

    private static Object instance(Class clazz) throws Exception{
        Object o = clazz.newInstance();
//        BEAN_SUPPLIER.putIfAbsent(clazz, () -> wrapIfNecessary(clazz, o));
//        NON_FIELD_BEAN_MAP.putIfAbsent(clazz, o);
        return o;
    }

    private static void injectField(Object instance, Map<String, Field> fieldMap) throws Exception {
        for (Map.Entry<String, Field> fieldEntry : MapUtils.emptyIfNull(fieldMap).entrySet()) {
            Field field = fieldEntry.getValue();
            Class<?> type = field.getType();
            Object toInject = complete(type);
            field.setAccessible(true);
            // 此处的写法有点ugly，因为 findRealInjectClass 会调用多次
            BEAN_MAP.put(findRealInjectClass(type), toInject);
            field.set(instance, toInject);
        }
    }

    public static Class findRealInjectClass(Class clazz) throws Exception {
        if (!clazz.isInterface()) {
            return clazz;
        }
        // 如果是接口，找到其实现类
        List<Class> satisfiedClazzList = new LinkedList<>();
        for (Map.Entry<Class, MetaNode> entry : META_NODE_MAP.entrySet()) {
            if (entry.getValue().getInterfaces().contains(clazz)) {
                satisfiedClazzList.add(entry.getKey());
            }
        }
        if (CollectionUtils.size(satisfiedClazzList) != 1) {
            throw new Exception("can not find satisfiedClazz for class:" + clazz.getSimpleName());
        }

        return satisfiedClazzList.get(0);

    }

    private static Object wrapIfNecessary(Class clazz, Object object) {
        if (CACHE_PROXYED.contains(clazz.getName())) {
            return object;
        }
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

        CACHE_PROXYED.add(clazz);
        return proxy;

    }

}

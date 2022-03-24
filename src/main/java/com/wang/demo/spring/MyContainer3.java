package com.wang.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/3/8 7:22 下午
 * 这个实现了复杂的循环依赖，双方都需要进行proxy：接口不生成BeanDefinition，只是作为实现类的BeanDefinition的属性
 * 这个实现不好，因为无法通过complete(interface.class)获取到对象
 */
@Slf4j
public class MyContainer3 {

    /**
     * 保存所有需要处理的类信息：包括类以及他们需要设置的属性
     */
    private static Map<Class, MetaNode> META_NODE_MAP = new HashMap<>();

    /**
     * 保存最终生成的实例
     */
    private static Map<Class, Object> BEAN_MAP = new HashMap<>();

    private static Map<Class, Object> NON_FIELD_BEAN_MAP = new HashMap<>();

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
        return NON_FIELD_BEAN_MAP.get(clazz);
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
        NON_FIELD_BEAN_MAP.entrySet().removeIf(it -> clazzSet.contains(it.getKey()));
        BEAN_MAP.putIfAbsent(clazz, instance);
    }

    private static Object complete(Class clazz) throws Exception {
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
        if (MapUtils.isNotEmpty(metaNode.getFieldMap())) {
            // 2. 属性设置
            injectField(instance, metaNode.getFieldMap());
        }

        // 3. aop
        Object proxyed = wrapIfNecessary(metaNode.getClazz(), instance);

        if (instance == proxyed) {
            if (!BEAN_MAP.containsKey(metaNode.getClazz())) {
                throw new Exception(String.format("clazz %s have been aop proxyed more than once", clazz));
            } else {
                return BEAN_MAP.get(metaNode.getClazz());
            }
        }

        // TODO: 2022/3/20
        /**
         * 1. 如果先进行aop再进行inject呢？
         */

        return proxyed;
    }

    private static void registerClass() throws Exception{
        registerClass(CycleService3.class, Collections.singletonList(CycleService3.class.getDeclaredField("cycleService4")), Collections.singletonList(MyInterface3.class));
        registerClass(CycleService4.class, Collections.singletonList(CycleService4.class.getDeclaredField("cycleService3")), Collections.singletonList(MyInterface4.class));
    }

    private static Object instance(Class clazz) throws Exception{
        Object o = clazz.newInstance();
        NON_FIELD_BEAN_MAP.putIfAbsent(clazz, o);
        return o;
    }

    private static void injectField(Object instance, Map<String, Field> fieldMap) throws Exception {
        for (Map.Entry<String, Field> fieldEntry : MapUtils.emptyIfNull(fieldMap).entrySet()) {
            Field field = fieldEntry.getValue();
            Class<?> type = field.getType();
            type = findRealInjectClass(type);
            Object toInject = complete(type);
            field.setAccessible(true);
            /**
             * 此处正是使用三级工厂的原因，如果只使用二级工厂
             * 那么此时譬如C1和C2循环依赖，当C2注入了一个proxy的C1之后，没有保存到某个地方
             * 再回到C1的complete过程中，此时会导致最后容器里的proxy-C1和C2注入的proxy-C1不是同一个对象
             * 我此处是让C2注入的proxy-C1直接覆盖了一级工厂（BEAN_MAP）里的对象，
             * 然后在回到C1的complete过程中，直接判断有没有一级工厂里有没有C1，如果有就直接返回，也就不会进行proxy的过程了
             */
            Object o = wrapIfNecessary(type, toInject);
            BEAN_MAP.putIfAbsent(type, o);
            field.set(instance, o);
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
        if (CACHE_PROXYED.contains(clazz)) {
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

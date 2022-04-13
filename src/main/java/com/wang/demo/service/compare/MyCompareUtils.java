package com.wang.demo.service.compare;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/4/13 6:48 下午
 */
public class MyCompareUtils {

    final static Map<Class, Function> SORT_FUNCTION = new HashMap<Class, Function>();

    static {
        Function<Father, String> function = Father::getName;
        Function<Son, String> function1 = Son::getName;
        Function<Toy, String> function2 = Toy::getName;
        SORT_FUNCTION.put(Father.class, function);
        SORT_FUNCTION.put(Son.class, function1);
        SORT_FUNCTION.put(Toy.class, function2);
    }

    public static void main(String[] args) {
        Toy toy1 = new Toy("111");
        Toy toy2 = new Toy("111");
        Toy toy3 = new Toy("222");
        Toy toy4 = new Toy("222");
        List<Toy> toyList = new ArrayList<>();
        toyList.add(toy1);
        toyList.add(toy3);
        List<Toy> toyList1 = new ArrayList<>();
        toyList1.add(toy4);
        toyList1.add(toy2);
        Son son1 = new Son("wang", toyList);
        Son son2 = new Son("wang", toyList);
        Son son3 = new Son("wang1", toyList1);
        Son son4 = new Son("wang1", toyList1);
        List<Son> sons1 = new ArrayList<>();
        List<Son> sons2 = new ArrayList<>();
        sons1.add(son1);
        sons1.add(son3);
        sons2.add(son4);
        sons2.add(son2);
        Father father1 = new Father("sheng", sons1);
        Father father2 = new Father("sheng", sons2);
        System.out.println(equals(Father.class, father1, father2));
    }

    public static  <T> boolean equals(Class clazz, T obj1, T obj2) {
        AtomicBoolean result = new AtomicBoolean(true);
        ReflectionUtils.doWithFields(clazz, field -> {
            field.setAccessible(true);
            Object value1 = field.get(obj1);
            Object value2 = field.get(obj2);
            Class<?> fieldClazz = field.getType();
            boolean isCollection = Collection.class.isAssignableFrom(fieldClazz);
            if (!isCollection) {
                if (!Objects.equals(value1, value2)) {
                    result.set(false);
                    return;
                }
            } else {
                boolean b = collectionEquals((Collection) value1, (Collection) value2);
                if (!b) {
                    result.set(false);
                    return;
                }
            }
        });
        return result.get();
    }

    public static <T> boolean collectionEquals(Collection collection1, Collection collection2) {
        if (CollectionUtils.isEmpty(collection1) && CollectionUtils.isEmpty(collection2)) {
            return true;
        }

        if (CollectionUtils.size(collection1) != CollectionUtils.size(collection2)) {
            return false;
        }

        if (List.class.isAssignableFrom(collection1.getClass())) {
            List list1 = (List) collection1;
            List list2 = (List) collection2;
            Class<?> clazz = list1.get(0).getClass();
            list1.sort(Comparator.comparing(SORT_FUNCTION.get(clazz)));
            list2.sort(Comparator.comparing(SORT_FUNCTION.get(clazz)));
            for (int i = 0; i < list1.size(); i++) {
                Object o1 = list1.get(i);
                Object o2 = list2.get(i);
                if (!equals(clazz, o1, o2)) {
                    return false;
                }
            }
            return true;
        }

        if (Set.class.isAssignableFrom(collection1.getClass())) {
            Set set1 = (Set) collection1;
            Set set2 = (Set) collection2;
            return set1.equals(set2);
        }

        return true;
    }

}

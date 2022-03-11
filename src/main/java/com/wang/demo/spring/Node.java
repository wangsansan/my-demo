package com.wang.demo.spring;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/3/8 3:56 下午
 */
@Slf4j
@NoArgsConstructor
@ToString
public class Node implements MyInterface {

    private Item item;

    public static void main(String[] args) {
        try {
            Constructor<Node> declaredConstructor = Node.class.getDeclaredConstructor();
            Node node = declaredConstructor.newInstance();
            Constructor<Item> itemConstructor = Item.class.getDeclaredConstructor();
            Item item = itemConstructor.newInstance();
            Field[] declaredFields = Node.class.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.getType().equals(Item.class)) {
                    field.setAccessible(true);
                    field.set(node, item);
                }
            }
            System.out.println(node);
        } catch (Exception e) {
            log.error("oops, there is an error", e);
        }

    }

    @Override
    public void doSomething() {
        System.out.println("node is doing something with item:" + item);
    }

    public void test() {
        System.out.println("it is a test");
    }
}

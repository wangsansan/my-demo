package com.wang.demo.offer.stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/4 8:00 上午
 */

import java.util.Stack;

/**
 * 用栈实现队列
 */
public class RunAsList<T> {

    private Stack<T> stack1 = new Stack<>();

    private Stack<T> stack2 = new Stack<>();

    public  void push(T obj) {
        stack1.push(obj);
    }

    public T pop() {
        if (empty()) {
            return null;
        }
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public T peek() {
        if (empty()) {
            return null;
        }
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }



    public static void main(String[] args) {
        RunAsList<String> list = new RunAsList<>();
        list.push("wang");
        list.push("chun");
        list.push("sheng");
        System.out.println(list.peek());
        System.out.println(list.pop());
        System.out.println(list.peek());
        System.out.println(list.pop());
        System.out.println(list.peek());
        System.out.println(list.pop());
        System.out.println(list.peek());
    }

}

package com.wang.demo.offer.stack;

import java.util.*;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/4 8:11 上午
 */
public class RunAsStack<T> {

    private Queue<T> queue1 = new LinkedList<>();
    private Queue<T> queue2 = new LinkedList<>();

    public void push(T obj) {
        queue1.add(obj);
    }

    public T pop() {
        if (empty()) {
            return null;
        }
        T result = null;
        if (queue1.size() == 1) {
            result = queue1.poll();
        }
        if (Objects.isNull(result)) {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            result = queue1.poll();
        }

        while (!queue2.isEmpty()) {
            queue1.add(queue2.poll());
        }
        return result;
    }

    public T top() {
        if (empty()) {
            return null;
        }
        if (queue1.size() == 1) {
            return queue1.peek();
        }
        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args) {
        RunAsStack<String> stack = new RunAsStack<>();
        stack.push("wang");
        stack.push("chun");
        stack.push("sheng");
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
    }

}

package com.wang.demo.offer.stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/9 4:27 下午
 */

import com.wang.demo.offer.sort.MyQuickSort;

import java.util.*;

/**
 * 计算出滑动窗口中最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 */
public class MovingWindow {

    static class MyQueue {

        Deque<Integer> queue = new LinkedList<>();

        public Deque<Integer> getQueue() {
            return queue;
        }

        public void add(int val) {
            while (!queue.isEmpty() && val > queue.getLast()) {
                queue.removeLast();
            }
            queue.add(val);
        }

        public void poll(int val) {
            while (!queue.isEmpty() && val ==  queue.peek()) {
                queue.poll();
            }
        }

        public int peek() {
            return queue.peek();
        }
    }

    public static int[] solution(int[] array, int k) {
        if (array == null || array.length < k) {
            return new int[0];
        }

        System.out.println(Arrays.toString(array));

        if (array.length == 1) {
            return array;
        }

        int[] result = new int[array.length - k + 1];
        MyQueue myQueue = new MyQueue();
        for (int i = 0; i < k; i++) {
            myQueue.add(array[i]);
            System.out.println(myQueue.getQueue());
        }
        int index = 0;
        result[index++] = myQueue.peek();
        for (int i = k; i < array.length; i++) {
            myQueue.poll(array[i - k]);

            myQueue.add(array[i]);

            System.out.println(myQueue.getQueue());

            result[index++] = myQueue.peek();
        }

        return result;
    }

    public static int func(int x) {
        if (x == 1) {
            return 1;
        }
        if (x == 2) {
            return 2;
        }

        return func(x - 1) + func(x - 2);
    }

    public static void main(String[] args) {
        int[] array = {1, 3, -1, -3, 5, 3 ,6 ,7};
        int k = 3;
        System.out.println(Arrays.toString(solution(array, k)));
    }

}

package com.wang.demo.offer.stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/12 8:56 上午
 */

import com.google.common.collect.Maps;

import java.util.*;

/**
 * 从队列里找出出现频率最高的前k个数
 * 时间复杂度O(nlogn)
 */
public class MostKNumber {

    public static int[] solution(int[] array, int k) {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            priorityQueue.add(entry);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll().getKey();
        }

        return result;
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 2, 2, 3, 5};
        int k = 2;
        System.out.println(Arrays.toString(solution(array, k)));
    }

}

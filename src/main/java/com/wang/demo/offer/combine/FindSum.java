package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/31 9:30 上午
 */

import java.util.LinkedList;
import java.util.List;

/**
 * 找出所有相加和为n的k个数的组合
 * 组合里只能包含正整数1到9，不得重复
 */
public class FindSum {

    private static int top_limit = 9;
    private static List<List<Integer>> result = new LinkedList<>();
    private static LinkedList<Integer> queue = new LinkedList<>();
    private static int sum = 0;

    public static void solution(int n, int k) {
        process(1, n, k);
    }

    public static void process(int start, int n, int k) {
        if (queue.size() == k) {
            if (sum == n) {
                result.add(new LinkedList<>(queue));
            }
            return;
        }
        for (int i = start; i <= top_limit && sum <= n; i++) {
            queue.add(i);
            sum += i;
            if (sum <= n) {
                process(i + 1, n, k);
            }
            queue.removeLast();
            sum -= i;
        }
    }

    public static void main(String[] args) {
        int n = 9, k = 3;
        solution(n, k);
        System.out.println(result);
    }

}

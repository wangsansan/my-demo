package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/29 9:08 上午
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个数n和一个数k
 * 找出1..n中所有k个数的组合
 */
public class FindCombining {

    private static List<List<Integer>> result = new LinkedList<>();

    private static LinkedList<Integer> path = new LinkedList<>();

    public static void solution2(int n, int k) {
        process2(k, 1, n);
    }

    private static void process2(int k, int startIndex, int n) {
        if (path.size() == k) {
            result.add(new LinkedList<>(path));
            return;
        }

        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            process2(k, i + 1, n);
            path.removeLast();
        }

    }

    public static List<List<Integer>> solution(int n, int k) {
        if (n < k) {
            return Collections.emptyList();
        }
        if (n == k) {
            List<Integer> result = new ArrayList<>(n);
            for (int i = 1; i < n; i++) {
                result.add(i);
            }
            return Collections.singletonList(result);
        }

        return process(1, n, k);
    }

    private static List<List<Integer>> process(int start, int end, int k) {
        if (k == 1) {
            List<List<Integer>> result = new ArrayList<>(end - start + 1);
            for (int i = start; i <= end; i++) {
                result.add(Collections.singletonList(i));
            }
            return result;
        }

        List<List<Integer>> dd = new LinkedList<>();
        for (int i = start; i <= end; i++) {
            List<Integer> result = new ArrayList<>(k);
            List<List<Integer>> tempResult = process(i + 1, end, k - 1);
            for (List<Integer> varResult : tempResult) {
                result.add(i);
                result.addAll(varResult);
                dd.add(new ArrayList<>(result));
                result.clear();
            }
        }
        return dd;
    }

    public static void main(String[] args) {
        int n = 8, k = 3;
        System.out.println(solution(n, k));
        solution2(n, k);
        System.out.println(result);
    }

}

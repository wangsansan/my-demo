package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/8 8:06 上午
 */

import com.sun.tools.javac.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * n皇后问题
 */
public class NQueenProblem {

    private static List<List<String>> result = new LinkedList<>();

    private static Stack<Pair<Integer, Integer>> path = new Stack<>();

    public static void solution(int n) {
        process(n, 0);
    }

    private static void process(int n, int startRow) {
        if (path.size() == n) {
            result.add(buildString(n));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!canPlace(startRow, i)) {
                continue;
            }
            path.push(Pair.of(startRow, i));
            process(n, startRow + 1);
            path.pop();
        }
    }

    private static boolean canPlace(int x, int y) {
        for (int i = 0; i < path.size(); i++) {
            Pair<Integer, Integer> index = path.get(i);
            // 如果行和列重复
            if (x == index.fst || y == index.snd) {
                return false;
            }
            // 对角线重复
            if (Math.abs(y - index.snd) == x - index.fst) {
                return false;
            }
        }

        return true;
    }

    private static List<String> buildString(int n) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < path.size(); i++) {
            result.add(buildString(path.get(i), n));
        }
        return result;
    }

    private static String buildString(Pair<Integer, Integer> index, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (index.snd != i) {
                stringBuilder.append(".");
            } else {
                stringBuilder.append("Q");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int n = 4;
        solution(n);
        System.out.println(result);
    }

}

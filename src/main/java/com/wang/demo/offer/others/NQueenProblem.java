package com.wang.demo.offer.others;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/29 10:05 上午
 */
public class NQueenProblem {

    private static List<List<Integer>> result = new ArrayList<>();

    static Stack<Integer> path = new Stack<>();

    public static void solution(int n) {
        process(n, 0);
    }

    private static void process(int n, int startRow) {
        if (conflict()) {
            return;
        }
        if (startRow == n) {
            if (path.size() != n) {
                return;
            }
        }
        if (path.size() == n) {
            if (!conflict()) {
                List<Integer> collect = path.stream().map(Integer::new).collect(Collectors.toList());
                result.add(collect);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            path.push(i);

            process(n, startRow + 1);

            path.pop();
        }

    }

    private static boolean conflict() {
        if (path.isEmpty()) {
            return false;
        }

        for (int i = 0; i < path.size(); i++) {
            for (int j = i + 1; j < path.size(); j++) {
                if (path.get(i).equals(path.get(j))) {
                    return true;
                }
                if (Math.abs((i - j)) == Math.abs(path.get(i) - path.get(j))) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void printQueen() {
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        for (int i = 0; i < result.size(); i++) {
            List<Integer> currentResult = result.get(i);
            for (int j = 0; j < currentResult.size(); j ++) {
                for (int k = 0; k < currentResult.get(j); k++) {
                    System.out.print("*");
                }
                System.out.println("Q");
            }
            System.out.println("===========================");
        }
    }

    public static void main(String[] args) {
        solution(8);
        printQueen();
    }

}

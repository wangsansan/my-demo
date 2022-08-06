package com.wang.demo.offer.combine;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/6 9:29 上午
 */

/**
 * 给出一个整数数组，找出里面所有的递增子序列
 */
public class AscSubGroup {

    private static List<List<Integer>> result = new LinkedList<>();

    private static Stack<Integer> path = new Stack<>();

    public static void solution(int[] nums) {
        process(nums, 0);
    }

    private static void process(int[] nums, int startIndex) {
        if (path.size() >= 2) {
            LinkedList<Integer> currentPath = new LinkedList<>(path);
            if (!result.contains(currentPath)) {
                result.add(currentPath);
            }
        }

        if (startIndex == nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            int current = nums[i];
            if (path.isEmpty() || current >= path.peek()) {
                path.push(current);
                process(nums, i + 1);
                path.pop();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        solution(nums);
        System.out.println(result);
    }

}

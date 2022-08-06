package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/6 9:21 上午
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个可能包含重复元素的整数数组nums，
 * 返回该数组所有可能的子集
 * 解集不能包含重复的子集
 */
public class SubGroup2 {

    private static List<List<Integer>> result = new LinkedList<>();

    private static Stack<Integer> path = new Stack<>();

    public static void solution(int[] nums) {
        Arrays.sort(nums);
        process(nums, 0);
    }

    private static void process(int[] nums, int startIndex) {
        result.add(new LinkedList<>(path));
        if (startIndex == nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            process(nums, i + 1);
            path.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3};
        solution(nums);
        System.out.println(result);
    }

}

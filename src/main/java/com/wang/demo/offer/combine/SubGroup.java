package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/5 7:37 上午
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一组不含重复元素的整数数组nums，返回该数组所有可能的子集
 * 子集不能重复
 */
public class SubGroup {

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
            path.push(nums[i]);
            process(nums, i + 1);
            path.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        solution(nums);
        System.out.println(result);
    }

}

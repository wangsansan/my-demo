package com.wang.demo.offer.dynamic;

import java.util.Arrays;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/3 9:03 上午
 */

/**
 * 给定一个只包含正整数的非空数组。
 * 是否可以将这个数组分割成两个子集，使得两个子集的元素和想等
 */
public class SplitArrayToSame {
    /**
     * 这道题目用01背包思想分析的话
     * 原本dp[i][j]是指在重量为j的背包中从0到i个物品中选取物品，求最大价值
     * 本题中由于价值和重量合二为一了：
     * dp[i][j]可以理解为在重量为j的背包中从0到i个物品中选取物品，最大重量是否为j，也就是背包是否能正好放满
     */
    public static boolean solution(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = Arrays.stream(nums)
                .reduce(0, Integer::sum);
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2 ;
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            // 此处01背包问题，为什么j的取值最大是target
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] ==  target;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(solution(nums));
    }

}

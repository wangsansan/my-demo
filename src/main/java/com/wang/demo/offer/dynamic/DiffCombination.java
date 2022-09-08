package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/8 8:41 上午
 */

import java.util.Arrays;

/**
 * 不同的组合数
 * 给定一个由正整数组成，且不存在重复数据的数组和一个目标和
 * 找出和为目标和的组合个数。
 * 注：不同顺序视为不同组合
 */
public class DiffCombination {

    public static int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[j] 表示和为j的组合个数
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // 遍历方式1
        for (int j = 0; j < dp.length; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        // 遍历方式2
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = nums[i]; j < dp.length; j++) {
//                dp[j] += dp[j - nums[i]];
//            }
//            System.out.println(Arrays.toString(dp));
//        }

        /**
         * 遍历方式1和遍历方式2 最大的不同在于遍历方式1求到的是排列数；遍历方式2求到的组合数
         * 差别在于：
         * 遍历方式1不管是在求dp[j]中任何一个数的时候都可以使用所有的排列数，也就是nums[0..i]里所有的数，依赖的dp[0..j-1]都会包含nums[i]
         * 遍历方式2在求dp[j]中的值的时候，依赖的都是不包含本次使用的nums[i]得到的dp[0..j-1]的值
         * 从而导致了差别
         */

        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5};
        int target = 6;
        System.out.println(solution(nums, target));
    }

}

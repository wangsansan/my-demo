package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/13 8:22 上午
 */

import java.util.Arrays;

/**
 * 给定一个数组，不能获取相邻的两个数值
 * 最大获取数值为多少
 */
public class Robbery {

    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 如果按照之前推理的01背包思想来解决这个问题，写出的答案就是下面这种！！！
     * 但是在写的过程中最终的dp[j] = Math.max(dp[j - 1], dp[j - 2] + nums[j]);
     * 没有使用任何和i相关的变量，所以整个解题思路退化成了一层for循环
     * 也类似 斐布拉契数列 的原始版本和无限版本
     * 当外侧的变量固定了的时候，两层for循环就可以退化成一层了
     */
    /**
     * 这道题和01背包差别最大的地方在于，01背包对于物品的判断属于可加or可不加，计算的正好是加或者不加情况下的最大值
     * 这道题是i-2可加、可不加；而i-1必然不能加
     */
    public static int solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 0; i < nums.length; i++) {
            System.out.println("i=========" + i);
            for (int j = dp.length - 1; j >= 2; j--) {
                /**
                 * 这道题和普通的背包问题差别在于，当前状态dp[i]不止和一个上一个状态相关，而是和多个上一个状态相关
                 * 与之比较类似的是完全背包求次数的问题，如果01背包求有多少种方式之类的，就和这道题比较接近了
                 * 这道题的转换，是不是代表着01背包问题：
                 *          只要涉及到和多个状态相关，且背包的状态取值是确定的，不再是dp[j-nums[i]]这种，都可以转换成一层for循环，且遍历从前往后
                **/
                dp[j] = Math.max(dp[j], Math.max(dp[j - 1], dp[j - 2] + nums[j]));
                System.out.println(Arrays.toString(dp));
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(solution(nums));
        System.out.println(solution1(nums));
        int[] nums1 = {2, 7, 9, 3, 1};
        System.out.println(solution(nums1));
        System.out.println(solution1(nums1));
    }

}

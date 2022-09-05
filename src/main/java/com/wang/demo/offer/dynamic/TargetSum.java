package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/5 8:00 上午
 */

import java.util.Arrays;

/**
 * 给定一个非负数数组，a1，a2.。。an，和一个目标数S
 * 现在你有两个符号+和-。对于数组中的任意一个整数，你可以从+和-
 * 中选择一个符号添加到前面。返回所有方法数
 */
public class TargetSum {

    public static int solution(int[] a, int S) {
        int sum = Arrays.stream(a)
                .reduce(0, Integer::sum);
        if (Math.abs(S) > sum) {
            return 0;
        }

        /**
         * 一个和为偶数的非负数组仅靠+和-无法凑成奇数；
         * 同理，一个和为奇数的非负数组仅靠+和-无法凑成偶数
         */
        if ((S + sum) % 2 == 1 ) {
            return 0;
        }

        // 本题使用01背包思路解决，最终需要解决的问题是使用+得到目标和有多少种方式，剩余的都是-
        // target - (sum - target) = S => target = (S + sum) / 2
        int target = (S + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < a.length; i++) {
            for (int j = target; j >= a[i]; j--) {
                // 此处有点像是回溯法，或者说是斐波拉契数列的无限版
                /**
                 * 斐波拉契数列无限版：对于一个n阶的楼梯，可以一次跨1和n步，那么n阶的楼梯一共有多少种方式跨过
                 * dp[n] = dp[1] +...+dp[n]
                 */
                dp[j] += dp[j - a[i]];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(solution(a, S));
    }

}

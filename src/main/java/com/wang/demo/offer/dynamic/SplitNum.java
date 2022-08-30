package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/30 8:07 上午
 */

import java.util.Arrays;

/**
 * 将整数拆成最少两个整数之和，拆开的整数求乘积
 * 求最大乘积
 */
public class SplitNum {

    public static int solution(int num) {
        if (num < 2) {
            return 0;
        }

        int[] dp = new int[num + 1];
        dp[2] = 1;
        for (int i = 3; i < dp.length; i++) {
            for (int j = 1; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[i-j] * j, (i - j) * j ));
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[num];
    }

    public static void main(String[] args) {
        int num = 10;
        System.out.println(solution(num));
    }

}

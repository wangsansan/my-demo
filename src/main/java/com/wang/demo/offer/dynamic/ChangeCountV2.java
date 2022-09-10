package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/10 9:16 上午
 */

import java.util.Arrays;

/**
 * 给定不同面额的硬币coins和一个总金额amount
 * 编写一个函数来计算可以凑成总金额所需的最小的硬币个数
 */
public class ChangeCountV2 {

    public static int solution(int[] coins, int amount) {
        // dp[i]代表凑成金额i所需要的最小的金币个数
        int[] dp = new int[amount + 1];
        /**
         * 因为组成金额0确实只需要0个硬币
         * 所以如果有个金额无法组成，不能初始化为0，也就是0是有具体含义的
         * 本题的难点就在于dp[0]的取值0是有意义的，所以初始化的时候，当i != 0的时候，dp[i]不能等于0
         */
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // 完全背包问题
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        System.out.println(solution(coins, amount));
    }

}

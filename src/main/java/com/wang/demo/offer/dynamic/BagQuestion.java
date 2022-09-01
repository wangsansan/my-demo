package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/1 8:37 上午
 */

/**
 * 01背包问题
 */
public class BagQuestion {

    public static int solution(int[] weights, int[] values, int bagSize) {
        //dp[i][j]代表放入在背包重量为j的前提下，放入第i个物品的重量
        int[][] dp = new int[weights.length][bagSize + 1];

        // 当放入第0个物品时，不同的背包重量，得到的结果是不一样的
        for (int j = 0; j <= bagSize; j++) {
            if (weights[0] > j) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = values[0];
            }
        }

        for (int i = 1; i < weights.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                // 如果剩下的重量不够放物品i了，那么dp[i][j] = dp[i - 1][j]
                if (j - weights[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 否则要不要放下物品，就需要判断了
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] values = {15, 20, 30};
        int bagSize = 4;
        System.out.println(solution(weight, values, bagSize));
    }
}

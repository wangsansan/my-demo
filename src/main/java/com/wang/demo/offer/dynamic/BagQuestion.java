package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/1 8:37 上午
 */

import java.util.Arrays;

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

    /**
     * 使用一维数组解决01背包问题
     * 注意此种解法，和上面的二维数组做参照，由于不再记录物品使用状态，导致可能同一个物品可能会被多次添加
     * 所以对背包重量逆序遍历，使得原本的先后顺序反过来，j较大的时候不再依赖较小值，也就不可能出现同一个物品多次添加被计算
     */
    public static int solution1(int[] weights, int[] values, int bagSize) {
        // dp[j] 表示背包容量为j的情况下，能放下最多的物品价值
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int j = bagSize; j > 0; j--) {
                if (j - weights[i] < 0) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                    System.out.println(Arrays.toString(dp));
                }
            }
        }

        return dp[bagSize];
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] values = {15, 20, 30};
        int bagSize = 4;
        System.out.println(solution(weight, values, bagSize));
        System.out.println(solution1(weight, values, bagSize));
    }
}

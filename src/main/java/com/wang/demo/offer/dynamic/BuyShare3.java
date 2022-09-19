package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/18 9:24 上午
 */

import java.util.Arrays;

/**
 * 买卖股票的最佳时机
 * 可以多次买入和卖出
 * 不能同时参与多只股票交易
 * 最多交易两次
 */
public class BuyShare3 {

    /**
     * 持有股票的时候，有可能现金是正整数
     */
    public static int solution(int[] prices) {
        return doProcess(prices);
    }

    public static int doProcess(int[] prices) {
        int[][] dp = new int[prices.length][5];
        /**
         * dp[i][0]代表截止到第i天，不操作
         * dp[i][1]代表截止到第i天，进行了第一次购买
         * dp[i][2]代表截止到第i天，进行了第一次股票售卖
         * dp[i][3]代表截止到第i天，进行了第二次购买
         * dp[i][4]代表截止到第i天，进行了第二次股票售卖
         */
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }


        /**
         * 理论上应该return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4])
         * 但是第二次买卖如果不挣钱，其实在上面的使用Math.max已经忽略掉这种情况了
         * 所以直接return dp[prices.length - 1][4]就可以了
         */
        return dp[prices.length - 1][4];
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(solution(prices));
        int[] prices1 = {7, 6, 4, 3, 1};
        System.out.println(solution(prices1));
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println(solution(prices2));
    }



}

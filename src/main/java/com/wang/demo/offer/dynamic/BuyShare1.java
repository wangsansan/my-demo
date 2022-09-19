package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/17 8:18 上午
 */

/**
 * 买卖股票的最佳时机
 */
public class BuyShare1 {

    /**
     * 做完BuyShare3之后才回头看这道题
     * dp[i][0]可以理解为，截止到第i天进行了第一次股票购买，拥有的现金
     * dp[i][1]可以理解为，截止到第i天进行了第一次股票售卖，拥有的现金
     */
    public static int solution(int[] prices) {
        int[][] dp = new int[prices.length][2];
        // dp[i][0]表示第i天持有股票时拥有现金
        // dp[i][1]表示第i天持有现金时拥有现金
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            // dp[i][0]:第i-1天可能持有股票也可能持有现金
            /**
             * 第i天持有股票：第i-1天持有股票的话，继续持有股票；第i-1天持有现金的话，直接买股票
             * 第i天持有现金：第i-1天持有股票的话，卖掉；第i-1天持有现金的话，继续持有现金
             */
            /**
             * important!!!
             * 本题的难点在于如果第i-1天持有现金，第i天想买股票，那么第i-1天的现金一定是0
             */
            dp[i][0] = Math.max(dp[i - 1][0], - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }

        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }

    public static int solution1(int[] prices) {
        int[] dp = new int[2];
        dp[0] = -prices[0];
        dp[1] = 0;
        for (int i = 0; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], -prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
        }
        return dp[1];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(solution(prices));
        System.out.println(solution1(prices));
        int[] prices1 = {7, 6, 4, 3, 1};
        System.out.println(solution(prices1));
        System.out.println(solution1(prices1));
        int[] prices2 = {2, 3, 4, 5, 1};
        System.out.println(solution(prices2));
        System.out.println(solution1(prices2));
    }

}

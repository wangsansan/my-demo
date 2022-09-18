package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/18 9:24 上午
 */

/**
 * 买卖股票的最佳时机
 * 可以多次买入和卖出
 * 不能同时参与多只股票交易
 */
public class BuyShare2 {

    /**
     * 持有股票的时候，有可能现金是正整数
     */
    public static int solution(int[] prices) {
        int[][] dp = new int[prices.length][2];
        /**
         * dp[i][0]代表第i天持有股票的现金
         * dp[i][1]代表第i天持有现金的现金
         */
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            /**
             * 第i天持有股票：第i-1天持有股票，只能继续持有股票；第i-1天持有现金，可以买股票
             * 第i天持有现金：第i-1天持有股票，卖股票；第i-1天持有现金，持有现金
             */
            /**
             * 这道题和购买股票第一个版本 BuyShare1 的题差别在于：当第i天想买股票，第i-1天持有现金时，第i-1天的现金数是多少？？？
             * 当只允许一次买卖的时候，第i-1天的现金数是0，因为如果第i天要买股票，证明之前根本没有买过股票，所以现金为0
             * 当允许多次买卖的时候，第i-1天的现金就可能是之前买卖股票的收益了！！！！
             */
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }

        return dp[prices.length - 1][1];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(solution(prices));
        int[] prices1 = {7, 6, 4, 3, 1};
        System.out.println(solution(prices1));
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println(solution(prices2));
    }



}

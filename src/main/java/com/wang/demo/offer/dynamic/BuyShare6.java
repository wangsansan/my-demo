package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/22 8:56 上午
 */

/**
 * 可以交易很多次，但是手续费fee
 */
public class BuyShare6 {

    public static int solution(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        /**
         * dp[i][0] = 持有股票的状态
         * dp[i][1] = 不持有股票的状态(售卖费用放这儿)
         */
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }

        return dp[prices.length - 1][1];
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(solution(prices, fee));
    }

}

package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/18 9:24 上午
 */

/**
 * 买卖股票的最佳时机
 * 可以多次买入和卖出
 * 不能同时参与多只股票交易
 * 最多交易两次
 */
public class BuyShare4 {

    /**
     * 持有股票的时候，有可能现金是正整数
     */
    public static int solution(int[] prices, int k) {
        int operationTime = 2 * k;
        int[][] dp = new int[prices.length][operationTime + 1];
        dp[0][0] = 0;
        /**
         * 参考BuyShare3
         * dp[i][j]
         * 如果j是基数：前i天进行过Math.floor(j / 2)次股票买入时剩下的现金
         * 如果j是偶数：前i天进行过Math.floor(j / 2)次股票售卖时剩下的现金
         */
        for (int j = 0; j < operationTime + 1; j ++) {
            if (j % 2 == 0) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = -prices[0];
            }
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < operationTime + 1; j++) {
                if (j % 2 == 0) {
                    /**
                     * 如果第i天，最多进行Math.floor(j / 2)次股票售卖
                     * 也许i-1天就已经进行过Math.floor(j / 2)次股票售卖
                     * 也许i-1天只进行过Math.floor(j - 1 / 2)次股票买入，所以需要进行股票售卖，
                     * 因为每次只能进行单笔交易，所以此处不用考虑i- 1天进行了少于Math.floor(j - 1 / 2)次股票购买的情况，不符合题意
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                } else {
                    /**
                     * 如果第i天，最多进行Math.floor(j / 2)次股票买入
                     * 也许i-1天就已经进行过Math.floor(j / 2)次股票买入了
                     * 也许i-1天只进行过Math.floor(j - 1 / 2)次股票售卖，所以需要进行股票买入
                     * 因为每次只能进行单笔交易，所以此处不用考虑i- 1天进行了少于Math.floor(j - 1 / 2)次股票售卖的情况，不符合题意
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                }
            }
        }
        return dp[prices.length - 1][operationTime];
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(solution(prices, 2));
        int[] prices1 = {7, 6, 4, 3, 1};
        System.out.println(solution(prices1, 2));
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println(solution(prices2, 2));
    }



}

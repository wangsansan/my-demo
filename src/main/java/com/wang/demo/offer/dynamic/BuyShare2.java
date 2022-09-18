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
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[prices.length - 1][1];
    }

    public static int solution1(int[] prices) {
        int[] dp = new int[2];
        dp[0] = -prices[0];
        dp[1] = 0;
        for (int i = 1; i < prices.length; i++) {
            /**
             * 除了保持原状
             * 1. 如果有股票，可以换现金
             * 2. 如果有现金，可以换股票
             */
            /**
             * 如果第i天买股票，发现用之前剩余现金买股票比一直持有股票更有钱的时候
             * 那么第i天持有现金的取值最大值不会变，不会因为卖股票而获得更多
             * 因为如果dp[0] = dp[1] - prices[i]；那么dp[1] = dp[1]就成了必然
             * 此处的必然是因为，如果发生了dp[0] = dp[1] - prices[i]，说明之前的股票交易挣钱了，且当前不存在股票交易，所以dp[0] + prices[i] 也就没意义了
             */
            dp[0] = Math.max(dp[0], dp[1] - prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
            System.out.println(Arrays.toString(dp));
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
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println(solution(prices2));
        System.out.println(solution1(prices2));
    }



}

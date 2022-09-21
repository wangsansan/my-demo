package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/18 9:24 上午
 */

/**
 * 买卖股票的最佳时机
 * 可以多次买入和卖出
 * 不能同时参与多只股票交易
 * 增加一个冷冻期，第i天售出股票，第i+1天不能买
 */
public class BuyShare5 {

    /**
     * 这道题的变态之处在于，
     * 第i-1天是买入的话，第i天如果不操作会保持买入状态，是个optional的状态
     * 第i-1天是售卖的话，第i天必然进入冷冻期，是必然的状态
     * 第i-1天是冷冻的话，第i天必然不是冷冻期，可以进入安稳期也可以进入买入 optional
     * 第i-1天是安稳期的话，第i天除了冷冻期，都可以进入
     */
    /**
     * 跟之前的题目相比：首先多了一种状态--冷冻期
     * 而且冷冻期和之前的两种状态（买入、售出）还不相同
     * 之前的两种状态（买入、售出）是不操作就可以延续的状态！！！！！
     * 而冷冻期是不操作也会结束的状态，达到一种新的状态，所以必须加一个辅助状态来标志这种新的状态！！！！！！
     * 所以增加一种冷冻期后的安稳期
     */
    public static int solution(int[] prices) {
        int[][] dp = new int[prices.length][5];
        /**
         * dp[i][0]：不操作
         * dp[i][1]: 股票买入
         * dp[i][2]: 股票售卖
         * dp[i][3]: 冷冻期
         * dp[i][4]: 冷冻期后的安稳期
         */
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = 0;
        dp[0][4] = 0;
        for (int i = 1; i < prices.length; i++) {
            /**
             * 买入：
             * 如果i-1天是买入，那么第i天可以保持买入
             * 如果i-1天是冷冻期，那么第i天可以买入
             * 如果i-1天是冷冻期之后的稳定期，那么第i天也可以买入
             */
            dp[i][1] = Math.max(dp[i - 1][1], Math.max(dp[i - 1][3], dp[i - 1][4]) - prices[i]);
            /**
             * 售卖：
             * 如果i-1天是买入，那么第i天可以进入售卖状态
             * 如果i-1天是售卖状态，那么第i天必然是冷冻期
             * 如果i-1天是冷冻期，那么第i天绝对不肯能是售卖期
             * 如果i-1天是冷冻期之后的稳定期，那么第i天也绝对不可能是售卖期
             */
            dp[i][2] = dp[i - 1][1] + prices[i];
            /**
             * 冷冻：
             * 如果i-1天是买入，那么第i天不是冷冻期
             * 如果i-1天是售卖状态，那么第i天必然是冷冻期
             * 如果i-1天是冷冻期，那么第i天绝对不是冷冻期
             * 如果i-1天是冷冻期之后的稳定期，那么第i天也绝对不可能是冷冻期
             */
            dp[i][3] = dp[i - 1][2];
            /**
             * 冷冻之后的安稳期：
             * 如果i-1天是买入，那么第i天不可能是安稳期
             * 如果i-1天是售卖状态，那么第i天必然是冷冻期，不是安稳期
             * 如果i-1天是冷冻期，那么第i天可以是安稳期
             * 如果i-1天是冷冻期之后的稳定期，那么第i天也可以是安稳期
             */
            dp[i][4] = Math.max(dp[i - 1][3], dp[i - 1][4]);
        }
        return dp[prices.length - 1][2];
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(solution(prices));
    }



}
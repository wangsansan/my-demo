package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/12 8:02 上午
 */

import org.apache.commons.lang3.ArrayUtils;

/**
 * 经典的买卖股票题
 * 一个数组表示某只股票每天的价格，求出最大利润
 */
public class BuyShare {

    public static int solution(int[] prices) {
        if (ArrayUtils.isEmpty(prices)) {
            return 0;
        }

        if (prices.length == 1) {
            return prices[0];
        }

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int gapProfit = prices[i] - prices[i - 1];
            if (gapProfit > 0) {
                profit += gapProfit;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5,3, 6, 4};
        System.out.println(solution(prices));
        int[] prices1 = {1, 2, 3, 4, 5};
        System.out.println(solution(prices1));
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println(solution(prices2));
    }

}

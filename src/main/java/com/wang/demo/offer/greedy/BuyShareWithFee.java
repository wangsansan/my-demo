package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/25 8:11 上午
 */

/**
 * 购买股票，有手续费，求最大取值
 */
public class BuyShareWithFee {

    public static int solution(int[] prices, int fee) {
        int minPrice = prices[0];
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                /**
                 * 此处取的永远是相邻两个节点的差值
                 * 如果相邻两个节点的差值大于0，就计入到总数里
                 */
                if (prices[i] > minPrice + fee) {
                    result += prices[i] - minPrice - fee;
                    /**
                     * 此处之所以把minPrice最后要一个减去fee，
                     * 是防止这步不是单调增长区间的最后一个，
                     * 而上面这步减去了fee，下面这步将减去fee的值赋值了minPrice
                     * 等下次再次执行上面这步，就把这次上面这步减去的fee加回来了
                     */
                    minPrice = prices[i] - fee;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(solution(prices, fee));
    }

}

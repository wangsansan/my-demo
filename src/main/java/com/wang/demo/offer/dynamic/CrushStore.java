package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/4 8:03 上午
 */

import java.util.Arrays;

/**
 * 两个石头相撞，x和y，相撞之后剩下的石头重量是y-x
 * 求，一堆石头，最后剩下的最小的石头重量是多少
 */
public class CrushStore {

    public static int solution(int[] stores) {
        if (stores == null || stores.length == 0) {
            return -1;
        }
        if (stores.length == 1) {
            return stores[0];
        }

        int sum = Arrays.stream(stores)
                .reduce(0, Integer::sum);
        int target = sum / 2;
        //dp[j]表示背包容量为j的情况下，能装下最多的石头重量
        // 参考SplitArrayToSame那道题就可以发现，那道题是这道题的特殊版本，最终判断 背包容量为j的情况下，能否正好装下重量为j的石头
        int[] dp = new int[target + 1];
        for (int i = 0; i < stores.length; i++) {
            for (int j = target; j  >= stores[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-stores[i]] + stores[i]);
            }
        }

        return sum - dp[target] - dp[target];
    }

    public static void main(String[] args) {

    }

}

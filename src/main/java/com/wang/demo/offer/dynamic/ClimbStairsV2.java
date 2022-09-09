package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/9 7:51 上午
 */

/**
 * 爬楼梯，每次可以爬m步，一共有n阶楼梯，有多少种方式
 */
public class ClimbStairsV2 {

    public static int solution(int n, int m) {
        /**
         * 本题解题思路是完全背包问题
         * 且是求排序而不是组合
         */
        // dp[i] 代表i阶楼梯有dp[i]中爬楼方式
        int[] dp = new int[n + 1];
        dp[1] = 1;
        /**
         * 这道题是从简单爬楼梯衍生过来的
         * 1. 如果有背包理论基础，按照背包理论写
         * 2. 如果没有背包理论基础，把之前简单爬楼梯的题稍微扩散下。
         * 发现两种思路写出来的代码居然一样！！！
         */
        for (int j = 2; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (j >= i) {
                    dp[j] += dp[j - i];
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 2, m = 2;
        System.out.println(solution(n, m));
    }

}

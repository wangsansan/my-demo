package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/11 9:46 上午
 */

/**
 * 给定一个整数n，返回和为n的完全平方数的最少数量
 */
public class WanQuanPingFangShu {

    public static int solution(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        // dp[i]代表和为i的完全平方数最少数量
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        /**
         * 当求最小次数、个数的时候，通常默认值设置为很大
         */
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        /**
         * 这个地方num的取值
         * 如果num取值直接用n/2
         * n > 0
         * (n/2) * (n/2) < n => n < 4，也就是只有n < 4的时候，n in (1, 2, 3)的时候才可能n更小
         * 但是当n in (1, 2, 3)的时候，n不是任何数的平方，此时num = 1是没有问题的
         */
        int num = n / 2;
        if (n % 2 == 1) {
            num += 1;
        }
        // 完全背包问题：两层for循环都从小往大遍历
        for (int i = 1; i <= num; i++) {
            for (int j = i * i; j < dp.length; j++) {
                if (dp[j - i * i] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(solution(n));
        n = 13;
        System.out.println(solution(n));
    }

}

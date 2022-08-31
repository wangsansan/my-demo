package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/31 9:02 上午
 */

/**
 * 给定一个整数n，求以1...n为节点组成的二叉搜索树有多少种
 */
public class TreeNum {

    public static int solution(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        // dp[n]代表有n种二叉搜索树
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        for (int i = 1; i <= n; i++){
            System.out.println(solution(i));
        }
    }

}

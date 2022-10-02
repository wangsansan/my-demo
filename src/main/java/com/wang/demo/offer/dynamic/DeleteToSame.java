package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/1 9:18 上午
 */

/**
 * 两个字符串，删除使得字符串相同
 */
public class DeleteToSame {

    public static int solution(String s, String t) {
        // dp[i-1][j-1]代表s下标为i-1、t变j-1时，公共子序列长度
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return s.length() + t.length() - 2 * dp[s.length()][t.length()];
    }

    public static int solution1(String s, String t) {
        // dp[i][j]代表s下标i-1和t下标j-1需要删去多少字符才能想等
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= t.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1), dp[i - 1][j - 1] + 2);
                }
            }
        }

        return dp[s.length()][t.length()];
    }

    public static void main(String[] args) {
        String s = "sea", t = "eat";
        System.out.println(solution(s, t));
        System.out.println(solution1(s, t));
    }

}

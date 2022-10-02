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

    public static void main(String[] args) {
        String s = "sea", t = "eat";
        System.out.println(solution(s, t));
    }

}

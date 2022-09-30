package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/30 7:18 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 给定字符串s和字符串t，计算在s的子序列中t出现的个数
 */
public class CountOfSubList {

    public static int solution(String s, String t) {
        if (StringUtils.isBlank(s) || StringUtils.isBlank(t)) {
            return 0;
        }
        // dp[i][j]代表s下标为i，t下标为j时，子序的个数
        int[][] dp = new int[s.length()][t.length()];
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            for (int k = 0; k <= i; k++) {
                if (sChars[k] == tChars[0]) {
                    count++;
                }
            }
            dp[i][0] = count;
        }
        for (int j = 1; j < t.length(); j++) {
            for (int i = j; i < s.length(); i++) {
                if (sChars[i] == tChars[j]) {
                    /**
                     * 题解这个地方 dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                     * 这题的难就难在当 s[i] == t[j]时，dp[i][j]的取值
                     * 当求个数、多少种之类的时候，就该考虑求和
                     * 而求最大长度的时候就该考虑使用max
                     */
//                    dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1]);
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

//                dp[i][j] = dp[i - 1][j];
//                if (sChars[i] == tChars[j]) {
//                    dp[i][j] += dp[i - 1][j - 1];
//                }
            }
        }

        return dp[s.length() - 1][t.length() - 1];
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(solution(s, t));
        ab;
        abb;
    }

}

package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/4 8:16 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串中最长回文子序列
 * 注意
 * 子串：是要连续的
 * 子序列：不需要连续
 */
public class MaxLengthOfHuiWen {

    public static int solution(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        int max = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(solution("bbab"));
    }

}

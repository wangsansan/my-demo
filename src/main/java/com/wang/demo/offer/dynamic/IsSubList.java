package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/29 7:52 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 给定字符串s和字符串t，判断s是不是t的子序列
 */
public class IsSubList {

    /**
     * 这道题因为放在动态规划下面，所以第一反应是使用动态规划做
     * 但是其实双指针遍历应该也可以实现
     * 切不可让刷题范围影响了思考
     * 思维定势，唉
     */
    public static boolean solution(String s, String t) {
        if (StringUtils.isBlank(s) || StringUtils.isBlank(t)) {
            return false;
        }
        // dp[i][j]代表s下标为i，t下标为j时，两者的子序长度
        int[][] dp = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(0, i + 1).contains(t.substring(0,1))) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int j = 0; j < t.length(); j++) {
            if (t.substring(0, j + 1).contains(s.substring(0, 1))) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
            }
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j < t.length(); j++) {
                if (sChars[i] == tChars[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s.length() - 1][t.length() - 1] == s.length();
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(solution(s, t));
        System.out.println(solution("axc", t));
    }

}

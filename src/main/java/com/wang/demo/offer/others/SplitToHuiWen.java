package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/2 8:23 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数 。
 */
public class SplitToHuiWen {

    /**
     * 利用贪心算法，求解结果不一定对
     * 看最后那个字符串 bcbcbbc
     */
    public static int solution(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (value.length() == 1) {
            return 0;
        }
        // dp[i][j]表示下标i到下标j是否是回文子串
        boolean[][] dp = new boolean[value.length()][value.length()];
        for (int i = value.length() - 1; i >= 0; i--) {
            for (int j = i; j < value.length(); j++) {
                if (value.charAt(i) !=  value.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        int count = 0;
        int i = 0;
        // 这个解法不一定对，这是贪心算法，不一定能得出最佳结果
        while (i < value.length()) {
            for (int j = value.length() - 1; j >= i; j--) {
                if (dp[i][j]) {
                    i = j + 1;
                    count++;
                }
            }
        }

        return count - 1;
    }

    public static int solution1(String value) {
        if (StringUtils.isBlank(value)) {
            return 0;
        }
        if (value.length() == 1) {
            return 0;
        }
        // dp[i]表示下标0到i本题需要最少切割多少次
        int[] dp = new int[value.length()];
        // 初始值必须设置，否则下面使用Math.min得不出结果
        for (int i = 0; i < value.length(); i++) {
            dp[i] = i;
        }
        for (int i = 0; i < value.length(); i++) {
            if (isHuiWen(value, 0, i)) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (isHuiWen(value, j + 1, i)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[value.length() - 1];
    }

    public static boolean isHuiWen(String value, int begin, int end) {
        while (begin <= end) {
            if (value.charAt(begin) != value.charAt(end)) {
                return false;
            } else {
                begin++;
                end--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("abba"));
        System.out.println(solution("abb"));
        System.out.println(solution("ab"));
        System.out.println(solution("a"));
        System.out.println(solution1("abba"));
        System.out.println(solution1("abb"));
        System.out.println(solution1("ab"));
        System.out.println(solution1("a"));
        System.out.println(solution("bcbcbbc"));
        System.out.println(solution1("bcbcbbc"));
    }

}

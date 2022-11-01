package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/1 8:04 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 求最长回文子串
 */
public class MaxLengthHuiWen {

    public static String solution(String value) {
        long start = System.currentTimeMillis();
        if (StringUtils.isBlank(value)) {
            return StringUtils.EMPTY;
        }
        if (value.length() == 1) {
            return value;
        }
        String max = StringUtils.EMPTY;
        for (int i = 0; i < value.length(); i++) {
            String s = process(value, i);
            if (s.length() > max.length()) {
                max = s;
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        return max;
    }

    private static String process(String value, int index) {
        String s1 = processOneIndex(value, index);
        String s2 = processTwoIndex(value, index, index + 1);
        if (s1.length() >= s2.length()) {
            return s1;
        } else {
            return s2;
        }
    }

    private static String processOneIndex(String value, int index) {
        if (index == 0 || index == value.length() - 1) {
            return String.valueOf(value.charAt(0));
        }
        int index1 = index - 1;
        int index2 = index + 1;

        if (value.charAt(index1) != value.charAt(index2)) {
            return String.valueOf(value.charAt(index));
        }

        return processTwoIndex(value, index1, index2);
    }

    private static String processTwoIndex(String value, int index1, int index2) {
        if (index1 < 0 || index2 >= value.length()) {
            return StringUtils.EMPTY;
        }

        if (value.charAt(index1) != value.charAt(index2)) {
            return StringUtils.EMPTY;
        }
        while (index1 >= 0 && index2 <= value.length() - 1) {
            if (value.charAt(index1) == value.charAt(index2)) {
                index1--;
                index2++;
            } else {
                break;
            }
        }

        return value.substring(index1 + 1, index2);
    }

    public static String solution1(String value) {
        long start = System.currentTimeMillis();
        if (StringUtils.isBlank(value)) {
            return StringUtils.EMPTY;
        }
        if (value.length() == 1) {
            return value;
        }
        boolean[][] dp = new boolean[value.length()][value.length()];
        for (int i = 0; i < value.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = value.length() - 1; i >= 0; i--) {
            for (int j = i; j < value.length(); j++) {
                if (value.charAt(i) != value.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (i == j || j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }
        String max = StringUtils.EMPTY;
        for (int i = 0; i < value.length(); i++) {
            for (int j = 0; j < value.length(); j++) {
                if (dp[i][j]) {
                    if ((j + 1 - i) > max.length()) {
                        max = value.substring(i, j + 1);
                    }
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(solution("babad"));
        System.out.println(solution("cbbd"));
        System.out.println(solution("ac"));
        System.out.println(solution("a"));

        System.out.println(solution1("babad"));
        System.out.println(solution1("cbbd"));
        System.out.println(solution1("ac"));
        System.out.println(solution1("a"));
    }

}

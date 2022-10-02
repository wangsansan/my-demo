package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/26 7:09 上午
 */

/**
 * 最长重复子序列
 */
public class MaxLengthSameSubList {

    /**
     * 动态规划的难点：
     * 1. 定义dp
     * 2. 取值逻辑：不能忘记dp的定义和已有只能用来进行判断的逻辑
     * 3. 初始化
     */
    public static int solution(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            if (str1.substring(0, i + 1).contains(str2.substring(0, 1))) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int j = 0; j < str2.length(); j++) {
            if (str2.substring(0, j + 1).contains(str1.substring(0, 1))) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
            }
        }

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int result = dp[0][0];
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                if (dp[i][j] > result) {
                    result = dp[i][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";
        System.out.println(solution(str1, str2));
    }

}

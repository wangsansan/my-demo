package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/3 9:03 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 回文个数
 */
public class CountOfHuiwen {

    public static int solution(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        int count = 0;
        boolean[][] dp = new boolean[str.length() + 1][str.length() + 1];
        for (int i = str.length() - 1; i >= 0; i--) {
            for (int j = i; j <= str.length() - 1; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    if (i == j) {
                        dp[i][j] = true;
                    } else if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution("abc"));
        System.out.println(solution("aaa"));
    }
}

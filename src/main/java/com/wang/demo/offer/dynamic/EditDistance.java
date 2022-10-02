package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/2 3:33 下午
 */

/**
 * 给定两个单词word1和word2，计算出将word1转换成word2所需的最少操作数
 * 可以插入
 * 可以删除
 * 可以替换
 */
public class EditDistance {

    public static int solution(String word1, String word2) {
        // dp[i][j]表示word1的下标i-1和word2下标j-1要编辑相等的编辑次数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(solution(word1, word2));
        System.out.println(solution("intention", "execution"));
    }

}

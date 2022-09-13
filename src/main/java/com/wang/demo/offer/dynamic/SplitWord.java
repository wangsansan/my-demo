package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/12 4:46 下午
 */

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 给定一个非空字符串s，和一个包含非空单词的列表WordDict，
 * 判断是是否可以被空格拆分为一个或多个在字段中出现的单词
 */
public class SplitWord {

    /**
     * 本题的难点在于定位for循环的参数
     */
    public static boolean solution(String s, Set<String> wordDict) {
        // dp[i]表示前i个字符组成的字符串能否被拆分
        boolean[] dp = new boolean[s.length() + 1];
        // 由于是完全背包问题，所以dp[0]需要设置为1或者true
        dp[0] = true;
        // 由于不是求次数之类的问题，所以不用考虑for循环的顺序
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < dp.length; j++) {
                dp[j] = dp[j] || ( dp[i] && wordDict.contains(s.substring(i, j)));
            }
        }

        return dp[s.length()];
    }

    private static int[] memo;

    public static boolean solution1(String s, Set<String> wordDict) {
        memo = new int[s.length()];
        return doProcess(s, 0, wordDict);
    }

    private static boolean doProcess(String s, int startIndex, Set<String> wordDict) {
        if (startIndex == s.length()) {
            return true;
        }
        if (memo[startIndex] == -1) {
            return false;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String subStr = s.substring(startIndex, i - startIndex + 1);
            if (!wordDict.contains(subStr)) {
                continue;
            }
            //只有找到第一个单词之后才继续往下找
            boolean res = doProcess(s, i + 1, wordDict);
            if (res) {
                return true;
            }
        }

        memo[startIndex] = -1;
        return false;
    }


    public static void main(String[] args) {
        String s = "leetcode";
        Set<String> wordDict = Sets.newHashSet("leet", "code");
        System.out.println(solution(s, wordDict));
        s = "applepenapple";
        wordDict = Sets.newHashSet("apple", "pen");
        System.out.println(solution(s, wordDict));
        s = "catsandog";
        wordDict = Sets.newHashSet("cats", "dog", "sand", "and", "cat");
        System.out.println(solution(s, wordDict));
    }

}

package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/6 8:28 上午
 */

/**
 * 给定一个二进制字符串数组strs和两个整数m和n
 * 找出并返回最大子集的大小，该子集中最多有m个0和n个1
 */
public class MaxNumSubGroup {

    public static int solution(String[] strs, int m, int n) {
        // dp[i][j]表示当0的个数<=i，1的个数<=j的时候，最大子集长度
        int[][] dp = new int[m + 1][n + 1];
        /**
         * 这道题是个二维01背包问题
         * 有两个上限，0的个数m和1的个数n
         * 而石头就是数组里的元素
         */
        for (String str : strs) {
            int zeroNum = 0;
            int oneNum = 0;
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                if (aChar == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zeroNum][j-oneNum] + 1);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(solution(strs, m, n));
    }

}

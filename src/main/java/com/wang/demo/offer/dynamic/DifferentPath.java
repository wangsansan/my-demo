package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/28 9:01 上午
 */

/**
 * 一个机器人位于一个m*n的网格的左上角，记为start
 * 每次只能向下或者向右，现在机器人要走到右下角网格
 * 共有多少种方式
 */
public class DifferentPath {

    public static int solution(int m, int n) {
        /**
         * dp[][]表示从坐标(0,0)到坐标(i,j)共有dp[i][j]种方式
         */
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int m = 2, n = 3;
        System.out.println(solution(m ,n));
        m = 3;
        n = 7;
        System.out.println(solution(m ,n));
        m = 7;
        n = 3;
        System.out.println(solution(m ,n));
        m = 3;
        n = 3;
        System.out.println(solution(m ,n));
    }
}

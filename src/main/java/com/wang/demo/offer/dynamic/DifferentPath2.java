package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/29 7:55 上午
 */

/**
 * 和DifferentPath是同一道题
 * 差别在于这个地图上有障碍：1是障碍，0是空位
 */
public class DifferentPath2 {

    public static int solution(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int m = map.length;
        int n = map[0].length;
        // dp[i][j]表示从 0,0 到 坐标 i,j共有多少种方式
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            if (map[i][0] == 0 && map[i -1][0] == 0) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }

        for (int j = 1; j < n; j++) {
            if (map[0][j] == 0 && map[0][j - 1] == 0) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] map = {
                {0,1},
                {0,0}
        };
        System.out.println(solution(map));
    }

}

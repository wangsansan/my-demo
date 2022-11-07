package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/7 8:57 上午
 */

/**
 * 463. 岛屿的周长
 */
public class IslandLength {

    public static int solution(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int[] directX = {-1, 1, 0, 0};
        int[] directY = {0, 0, -1, 1};
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + directX[k];
                        int y = j + directY[k];
                        /**
                         * 如果当前岛屿靠边，那么周长也要算
                         */
                        // 当前位置是陆地，并且从当前位置4个方向扩展的“新位置”是“水域”或“新位置“越界，则会为周长贡献一条边
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                            result++;
                        }
                    }
                }
            }
        }

        return result;
    }

    public static int solution1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int islandCount = 0;
        int coverCount = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    islandCount++;
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        coverCount++;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        coverCount++;
                    }
                }
            }
        }

        return islandCount * 4 - 2 * coverCount;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(solution(grid));
        System.out.println(solution1(grid));
    }

}

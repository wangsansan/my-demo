package com.wang.demo.offer8.array;

import java.util.Arrays;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/6 21:09
 */
public class Spin59 {

    public static int[][] solution(int n) {
        int[][] result = new int[n][n];
        int startRow = 0, startCol = 0;
        int endRow = n - 1, endCol = n - 1;
        int value = 1;
        int i = 0, j = 0;
        while (value <= n * n) {
            i = startRow;
            j = startCol;
            for (; j <= endCol; j++) {
                result[i][j] = value++;
            }
            j--;
            i++;
            for (; i <= endRow; i++) {
                result[i][j] = value++;
            }
            i--;
            j--;
            for (; j >= startCol; j--) {
                result[i][j] = value++;
            }
            j++;
            i--;
            for (; i > startRow; i--) {
                result[i][j] = value++;
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] solution = solution(1);
        for (int i = 0; i < solution.length; i++) {
            System.out.println(Arrays.toString(solution[i]));
        }
    }

}

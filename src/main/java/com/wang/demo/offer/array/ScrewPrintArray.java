package com.wang.demo.offer.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/17 8:23 上午
 */

import java.util.Arrays;

/**
 * 给定一个正整数n，返回螺旋数组
 */
public class ScrewPrintArray {

    public static int[][] solution(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        int startRow = 0, startCol = 0, endRow = n - 1, endCol = n - 1;
        while (startCol <= endCol && startRow <= endRow) {
            int row = startRow;
            int col = startCol;
            for (; col < endCol; col++) {
                result[row][col] = num++;
            }
            for (; row < endRow; row++) {
                result[row][col] = num++;
            }
            for (; col > startCol; col--) {
                result[row][col] = num++;
            }
            for (; row > startRow; row--) {
                result[row][col] = num++;
            }
            if (startRow < n - 1) {
                startRow++;
            }
            if (startCol < n - 1) {
                startCol++;
            }
            if (endRow > 0) {
                endRow--;
            }
            if (endCol > 0) {
                endCol--;
            }
            if ((startRow == endRow) && startRow == startCol && endCol == startCol) {
                result[startRow][startCol] = num;
                break;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int n = 6;
        int[][] solution = solution(n);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(solution[i]));
        }
    }

}

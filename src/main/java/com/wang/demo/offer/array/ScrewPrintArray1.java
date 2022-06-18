package com.wang.demo.offer.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/17 8:23 上午
 */

import java.util.Arrays;

/**
 * 给定一个正整数n，返回螺旋数组
 */
public class ScrewPrintArray1 {

    public static int[][] solution(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        int start = 0, end = n - 1;
        while (start <= end ) {
            int row = start;
            int col = start;
            for (; col < end; col++) {
                result[row][col] = num++;
            }
            for (; row < end; row++) {
                result[row][col] = num++;
            }
            for (; col > start; col--) {
                result[row][col] = num++;
            }
            for (; row > start; row--) {
                result[row][col] = num++;
            }
            if (start < n - 1) {
                start++;
            }
            if (end > 0) {
                end--;
            }
            if (start == end) {
                result[start][end] = num;
                break;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int n = 1;
        int[][] solution = solution(n);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(solution[i]));
        }
    }

}

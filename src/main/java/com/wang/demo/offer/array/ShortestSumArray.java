package com.wang.demo.offer.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/16 7:54 上午
 */

/**
 * 给定一个含有n个正整数的数组和一个正整数s，
 * 找出该数组中满足其和 >= s的长度最小的连续子数组，
 * 并返回长度，如果不存在就返回0
 */
public class ShortestSumArray {

    public static int solution(int[] array, int n) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int faster = 0, slower = 0;
        int currentResult = 0;
        int currentLength = 0;
        int currentSum = 0;
        while (faster < array.length) {
                currentSum += array[faster++];
                currentLength++;
                while (currentSum >= n && slower < array.length) {
                    if (currentResult == 0 || currentResult > currentLength) {
                        currentResult = currentLength;
                    }
                    currentSum -= array[slower++];
                    currentLength--;
                }
        }

        return currentResult;
    }

    public static void main(String[] args) {
        int[] array = {2,3,1,2,4,3};
        int n = 7;
        System.out.println(solution(array, 7));
    }

}

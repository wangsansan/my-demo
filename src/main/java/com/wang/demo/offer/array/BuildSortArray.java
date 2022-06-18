package com.wang.demo.offer.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/15 8:00 上午
 */

import java.util.Arrays;

/**
 * 给定一个单调不递减的数组，输出数组的平方值组成的数组，且单调不递减
 */
public class BuildSortArray extends AbstractArray {

    public static int[] solution(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        int[] result = new int[array.length];
        int begin = 0;
        int end = array.length - 1;
        int index = result.length - 1;
        while (begin < end) {
            int beginValue = array[begin] * array[begin];
            int endValue = array[end] * array[end];
            if (beginValue >= endValue) {
                result[index--] = beginValue;
                begin++;
            } else {
                result[index--] = endValue;
                end--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(solution(array)));
    }

}

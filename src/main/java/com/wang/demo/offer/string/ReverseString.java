package com.wang.demo.offer.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/29 8:15 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 给定一个字符串s和一个整数k
 * 每隔2k个字符，反转前k个字符
 * 如果剩余小于k，就全都反转
 */
public class ReverseString {

    public static String solution(String str, int k) {
        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        char[] array = str.toCharArray();
        int gap = 2 * k;
        for (int i = 0; i < array.length; i += gap) {
            int index2 = Math.min(i + k - 1, array.length - 1);
            doSolution(array, i, index2);
        }

        return new String(array);
    }

    private static void doSolution(char[] array, int index1, int index2) {
        int i = index1, j = index2;
        while (i < j) {
            doReverse(array, i, j);
            i++;
            j--;
        }
    }

    private static void doReverse(char[] array, int index1, int index2) {
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        int k = 2;
        System.out.println(solution(str, k));
    }

}

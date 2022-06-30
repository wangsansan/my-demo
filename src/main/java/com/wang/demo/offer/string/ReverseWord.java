package com.wang.demo.offer.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/30 8:11 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 反转单词
 */
public class ReverseWord {

    public static String solution(String str) {
        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        if (!str.contains(" ")) {
            return str;
        }

        str = str.trim();
        char[] array = str.toCharArray();
        int countOfBlank = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                countOfBlank++;
            }
        }
        if (countOfBlank == 1) {
            return str;
        }

        char[] result = new char[str.length()];

        int j = array.length - 1;
        for (int i = 0; i < result.length; i++) {
            result[i] = array[j--];
        }

        int from = 0;
        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                doReverse(result, from, i);
            } else if (result[i] == ' ') {
                doReverse(result, from, i - 1);
                from = i + 1;
            }
        }

        return new String(result);
    }

    private static void doReverse(char[] array, int index1, int index2) {
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        String str = " the sky is blue ";
        System.out.println(solution(str));
    }
}

package com.wang.demo.offer.string;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/30 7:58 上午
 */

/**
 * 字符串里的空格替换为20%
 */
public class ReplaceString {

    public static String solution(String str) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        char[] array = str.toCharArray();
        int countOfBlank = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                countOfBlank++;
            }
        }

        char[] result = new char[array.length + countOfBlank * 2];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != ' ') {
                result[j++] = array[i];
            } else {
                result[j++] = '%';
                result[j++] = '2';
                result[j++] = '0';
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        String str = "we are family";
        System.out.println(solution(str));
    }

}

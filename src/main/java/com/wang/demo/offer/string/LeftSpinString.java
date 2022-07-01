package com.wang.demo.offer.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/1 8:35 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 左旋字符串
 */
public class LeftSpinString extends AbstractString {

    public static String solution(String str, int k) {
        if (StringUtils.isBlank(str) || str.length() <= k) {
            return str;
        }

        char[] array = str.toCharArray();
        reverse(array, 0, k - 1);
        reverse(array, k, str.length() - 1);
        reverse(array, 0, str.length() - 1);
        return new String(array);
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        int k = 2;
        System.out.println(solution(str, k));
    }

}

package com.wang.demo.offer8.string;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/8 17:38
 */
public class ReverseString344 {

    public static void solution(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

}

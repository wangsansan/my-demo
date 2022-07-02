package com.wang.demo.offer.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/2 10:58 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串中寻找子串
 */
public class StrString {

    public static int solution(String str1, String str2) {
        if (StringUtils.isBlank(str1)) {
            return -1;
        }
        if (StringUtils.isBlank(str2)) {
            return 0;
        }


        char[] array = str1.toCharArray();
        char[] array1 = str2.toCharArray();
        for (int i = 0; i < array.length; i++) {
            boolean has = has(array, i, array1);
            if (has) {
                return i;
            }
        }

        return -1;
    }

    private static boolean has(char[] array, int index, char[] array1) {
        if ((array.length - index) < array1.length) {
            return false;
        }
        int i = index;
        int j = 0;
        while (i < array.length && j < array1.length) {
            if (array[i] == array1[j]) {
                i++;
                j++;
            } else {
                break;
            }
        }

        // 注意此处，因为上面有j++，所以如果字符串匹配成功的情况，j == array1.length
        if (j == array1.length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "hello";
        String str1 = "lla";
        System.out.println(solution(str, str1));
    }

}

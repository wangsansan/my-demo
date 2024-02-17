package com.wang.demo.offer8.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/16 08:03
 */
public class RightSpinString {

    public static String solution(String s, int k) {
        if (s == null || s.length() <= k) {
            return s;
        }
        char[] chars = s.toCharArray();
        process(chars, 0, chars.length - 1);
        process(chars, 0, k - 1);
        process(chars, k, chars.length - 1);
        return new String(chars);
    }

    private static void process(char[] chars, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        while (index1 < index2) {
            char temp = chars[index1];
            chars[index1] = chars[index2];
            chars[index2] = temp;
            index1++;
            index2--;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("abc", 2));
    }

}

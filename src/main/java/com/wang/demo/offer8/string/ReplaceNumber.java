package com.wang.demo.offer8.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/14 20:21
 */
public class ReplaceNumber {

    public static String solution(String s) {
        int l = s.length();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '1' && c <= '9') {
                l += 5;
            }
        }
        char[] chars = new char[l];
        int index = l - 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c >= '1' && c <= '9') {
                chars[index--] = 'r';
                chars[index--] = 'e';
                chars[index--] = 'b';
                chars[index--] = 'm';
                chars[index--] = 'u';
                chars[index--] = 'n';
            } else {
                chars[index--] = c;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(solution("a1b2c3"));
        System.out.println(solution("a5b"));
    }

}

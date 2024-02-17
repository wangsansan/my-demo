package com.wang.demo.offer8.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/14 16:04
 */
public class ReverseString542 {

    public static String solution(String s, int k) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int index = 0;
        while (index < chars.length) {
            int right = Math.min(index + k - 1, chars.length - 1);
            process(chars, index, right);
            index += 2 * k;
        }
        return new String(chars);
    }

    private static void process(char[] chars, int left, int right) {
        if (left == right) {
            return;
        }
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("abcdefghik", 2));
    }
}

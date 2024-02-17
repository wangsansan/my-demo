package com.wang.demo.offer8.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/15 16:04
 */
public class ReverseWord151 {

    public static String solution(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int index1 = 0, index2 = chars.length - 1;
        while (chars[index1] == ' ') {
            index1++;
        }
        while (chars[index2] == ' ') {
            index2--;
        }
        char[] newChar = new char[index2 - index1 + 1];
        int index = 0;
        for (int i = index1; i <= index2; i++) {
            if (chars[i] != ' ') {
                newChar[index++] = chars[i];
            } else {
                if (chars[i - 1] != ' ') {
                    newChar[index++] = ' ';
                }
            }
        }
        process(newChar, 0, index - 1);
        int start = 0;
        for (int i = 0; i < index; i++) {
            if (newChar[i] == ' ') {
                process(newChar, start, i - 1);
                start = i + 1;
            }
        }
        process(newChar, start, index - 1);
        return new String(newChar, 0, index);
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

    /**
     * 通过类似移出元素的方法来实现，减少空间复杂度
     */
    public static String solution1(String s) {
        char[] chars = s.toCharArray();
        int slow = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (slow != 0) {
                    chars[slow++] = ' ';
                }
                while (i < s.length() && chars[i] != ' ') {
                    chars[slow++] = chars[i++];
                }
            }
        }
        process(chars, 0, slow - 1);
        int start = 0;
        for (int i = 0; i < slow; i++) {
            if (chars[i] == ' ') {
                process(chars, start, i - 1);
                start = i + 1;
            }
        }
        process(chars, start, slow - 1);
        return new String(chars, 0, slow);
    }

    public static void main(String[] args) {
        System.out.println(solution(" hello   world "));
        System.out.println(solution1(" hello   world "));

    }

}

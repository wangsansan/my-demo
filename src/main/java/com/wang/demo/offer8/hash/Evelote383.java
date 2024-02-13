package com.wang.demo.offer8.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/12 16:13
 */
public class Evelote383 {

    public static boolean solution(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0) {
            return true;
        }

        if (magazine == null || magazine.length() < ransomNote.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(magazine.length());
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            int left = map.getOrDefault(c, 0) - 1;
            if (left < 0) {
                return false;
            }
            map.put(c, left);
        }
        return true;
    }

    /**
     * 凡是判断的是字母的单词是否相同类，都可以折算成数组。
     * 因为字母的个数是有限的，时间复杂度相对比较固定
     */
    public static boolean solution1(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0) {
            return true;
        }

        if (magazine == null || magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] times = new int[26];
        for (char c : magazine.toCharArray()) {
            times[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            times[c - 'a']--;
            if (times[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("aa", "ab"));
    }

}

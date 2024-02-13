package com.wang.demo.offer8.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/8 09:05
 */
public class SameC242 {

    public static boolean solution(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = processCTimes(s);
        Map<Character, Integer> tMap = processCTimes(t);
        if (sMap.size() != tMap.size()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            Integer sTime = sMap.getOrDefault(c, 0);
            Integer tTime = tMap.getOrDefault(c, 0);
            if (!sTime.equals(tTime)) {
                return false;
            }
        }

        return true;
    }

    public static boolean solution1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] index = new int[26];
        for (char c : s.toCharArray()) {
            index[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            index[c - 'a']--;
            if (index[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }

    private static Map<Character, Integer> processCTimes(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer oldTime = map.getOrDefault(c, 0);
            map.put(c, oldTime + 1);
        }
        return map;
    }


    public static void main(String[] args) {

    }

}

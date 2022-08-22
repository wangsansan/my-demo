package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/22 8:10 上午
 */

import java.util.*;

/**
 * 字符串由小写字母组成，要把字符串划分为尽可能多的片段
 * 同一个字母最多出现在一个片段中
 */
public class SplitString {

    public static List<List<Character>> solution(String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            map.put(aChar, i);
        }
        int currentEnd = 0;
        List<Character> currentResult = new LinkedList<>();
        List<List<Character>> result = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];
            int right = Math.max(i, map.get(current));
            if (currentResult.isEmpty() || right > currentEnd) {
                currentEnd = right;
            }
            currentResult.add(current);
            // 如果i一直追不上currentEnd，那么这个队列就没法划分出来
            if (i == currentEnd) {
                result.add(new LinkedList<>(currentResult));
                currentResult.clear();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String str = "ababcbacadefegdehijhklij";
        System.out.println(solution(str));
    }

}

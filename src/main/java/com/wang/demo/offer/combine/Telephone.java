package com.wang.demo.offer.combine;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/31 9:54 上午
 */

/**
 * 给定一个仅包含数字2-9的组合，返回所有它能表示的字母组合
 * 数字和字母的映射按照打字法的9宫格
 */
public class Telephone {

    private static Map<Integer, List<String>> map = new HashMap<Integer, List<String>>() {
        {
            put(2, Arrays.asList("a", "b", "c"));
            put(3, Arrays.asList("d", "e", "f"));
            put(4, Arrays.asList("g", "h", "i"));
            put(5, Arrays.asList("j", "k", "l"));
            put(6, Arrays.asList("m", "n", "o"));
            put(7, Arrays.asList("p", "q", "r", "s"));
            put(8, Arrays.asList("t", "u", "v"));
            put(9, Arrays.asList("w", "x", "y", "z"));
        }
    };

    private static List<String> result = new LinkedList<>();
    private static Stack<String> path = new Stack<>();

    public static void solution(String str) {
        char[] chars = str.toCharArray();
        process(chars, 0);
    }

    private static void process(char[] chars, int start) {
        if (path.size() == chars.length) {
            result.add(String.join("", path));
            return;
        }
        for (int i = start; i < chars.length; i++) {
            int value = chars[i] - ('1' - 1);
            List<String> current = map.get(value);
            for (String s : current) {
                path.push(s);
                process(chars, i + 1);
                path.pop();
            }
        }
    }

    public static void main(String[] args) {
        String str = "234";
        solution(str);
        System.out.println(result);
    }

}

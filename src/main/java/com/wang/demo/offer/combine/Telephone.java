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

    /**
     * 对照这道题和 FindSum 以及 FindCombining的解法差别
     * 为什么这个解法中间没有一次主的for循环
     * 因为这个题目不需要回溯，而不管是数字组合，还是求和数字组合都是涉及到回溯剪枝的
     * 或者可以理解为剪枝的节点取值不一样了：
     * 之前的两道题的节点取值可以通过for循环来限定，而这道题的节点取值只能靠映射字母来限定
     * @param chars
     * @param index
     */
    private static void process(char[] chars, int index) {
        if (path.size() == chars.length) {
            result.add(String.join("", path));
            return;
        }
        int value = chars[index] - ('1' - 1);
        List<String> current = map.get(value);
        for (String s : current) {
            path.push(s);
            process(chars, index + 1);
            path.pop();
        }
    }

    public static void main(String[] args) {
        String str = "23";
        solution(str);
        System.out.println(result);
    }

}

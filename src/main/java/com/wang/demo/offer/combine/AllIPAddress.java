package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/4 8:03 上午
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个仅包含数字的字符串
 * 复原它，并返回所有可能的IP地址
 */
public class AllIPAddress {

    private static List<List<Integer>> result = new LinkedList<>();

    private static Stack<Integer> path = new Stack<>();

    private static int num = 0;

    public static void solution(String str) {
        process(str, 0);
    }

    private static void process(String str, int startIndex) {
        if (path.size() == 4) {
            if (startIndex == str.length()) {
                result.add(new LinkedList<>(path));
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            int endIndex = startIndex + i;
            if (endIndex > str.length()) {
                break;
            }
            // 0开头的非0字符串直接break
            String sub1 = str.substring(startIndex, endIndex);
            if (sub1.startsWith("0") && sub1.length() > 1) {
                break;
            }
            int value = Integer.parseInt(sub1);
            if (value >= 0 && value <= 255) {
                path.push(value);
                process(str, endIndex);
                path.pop();
            }
        }
    }

    public static void main(String[] args) {
        String str = "0000";
        solution(str);
        System.out.println(result);
        result.clear();
        path.clear();
        str = "1111";
        solution(str);
        System.out.println(result);
        result.clear();
        path.clear();
        str = "010010";
        solution(str);
        System.out.println(result);
        result.clear();
        path.clear();
        str = "101023";
        solution(str);
        System.out.println(result);
        result.clear();
        path.clear();
        str = "25525511135";
        solution(str);
        System.out.println(result);
    }

}

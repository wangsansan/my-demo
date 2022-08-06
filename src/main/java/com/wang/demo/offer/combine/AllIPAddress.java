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
        /**
         * 此处的i是为了控制每一层的结束index，每一层的开始index其实是一定的，也就是上面传下来的
         * 同理求回文那道题，由于每一层的开始index一定是上一层的结束index，然后结束index可以不使用，因为可以每一层的结束index是startIndex + 1
         * 其实这道题和求回文那道题都是切割问题，其他的都是组合问题。
         * 切割问题和组合问题的差别就在于同一层的startIndex会不会变：切割问题不会变，组合问题会变
         * 不过切割问题的endIndex可能会变：这道题和判断回文问题的差别
         */
        for (int i = 1; i <= 3; i++) {
            int endIndex = startIndex + i;
            // endIndex = str.length()也是合理的
            if (endIndex > str.length()) {
                break;
            }
            // 0开头的非0字符串直接break，subString是左开右闭[x,y)
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

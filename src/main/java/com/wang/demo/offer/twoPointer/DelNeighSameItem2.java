package com.wang.demo.offer.twoPointer;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/6 8:12 上午
 */

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 删除字符串中的相邻重复项，连续出现两次的删除，直到删除的不能再删除
 */
public class DelNeighSameItem2 {

    public static String solution(String str) {
        if (StringUtils.isBlank(str) || str.length() == 1) {
            return str;
        }

        char[] array = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            if (!stack.isEmpty() && stack.peek().equals(array[i])) {
                stack.pop();
            } else {
                stack.push(array[i]);
            }
        }

        return stack.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String str = "abbaaaca";
        System.out.println(solution(str));
    }

}

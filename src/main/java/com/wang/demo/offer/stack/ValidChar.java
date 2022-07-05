package com.wang.demo.offer.stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/5 7:49 上午
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * 有效的括号
 */
public class ValidChar {

    public static boolean solution(char[] chars) {
        if (chars == null || chars.length == 0) {
            return true;
        }
        Map<Character,Character> match = new HashMap<>();
        match.put(')','(');
        match.put('}','{');
        match.put(']','[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (match.containsValue(chars[i])) {
                stack.push(chars[i]);
            } else if (match.containsKey(chars[i])) {
                Character top = stack.peek();
                if (Objects.nonNull(top) && match.get(chars[i]).equals(top)) {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "([]{)}";
        System.out.println(solution(str.toCharArray()));
    }

}

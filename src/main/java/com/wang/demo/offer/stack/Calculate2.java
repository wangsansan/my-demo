package com.wang.demo.offer.stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/7 7:58 上午
 */

import com.google.common.collect.Sets;

import java.util.*;

/**
 * 逆波兰式，根据算式，算出答案 2， 1， +， 3， * = （2 + 1）* 3 = 9
 */
public class Calculate2 {

    public static int solution(List<String> array) {
        Stack<String> stack = new Stack<>();
        Set<String> operations = Sets.newHashSet("+", "-", "*", "/");
        for (int i = 0; i < array.size(); i++) {
            if (operations.contains(array.get(i))) {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(calculate(first, second, array.get(i))));
            } else {
                stack.push(array.get(i));
            }
        }

        return Integer.parseInt(stack.peek());
    }

    private static Integer calculate(int left, int right, String operation) {
        switch (operation) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String str = "2,1,+,3,*";
        System.out.println(solution(Arrays.asList(str.split(","))));
        str = "4,13,5,/,+";
        System.out.println(solution(Arrays.asList(str.split(","))));
        str = "10,6,9,3,+,-11,*,/,*,17,+,5,+";
        System.out.println(solution(Arrays.asList(str.split(","))));
    }

}

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
public class Calculate {

    public static int solution(List<String> array) {
        Stack<String> stack = new Stack<>();
        Set<String> operations = Sets.newHashSet("+", "-", "*", "/");
        Integer current = null;
        for (int i = 0; i < array.size(); i++) {
            if (operations.contains(array.get(i))) {
                if (Objects.isNull(current)) {
                    int second = Integer.parseInt(stack.pop());
                    int first = Integer.parseInt(stack.pop());
                    current = calculate(first, second, array.get(i));
                } else {
                    int first = Integer.parseInt(stack.pop());
                    current = calculate(first, current, array.get(i));
                }
            } else {
                stack.push(array.get(i));
            }
        }

        return Objects.isNull(current) ? 0 : current;
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

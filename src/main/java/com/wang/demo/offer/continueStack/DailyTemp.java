package com.wang.demo.offer.continueStack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/5 7:47 上午
 */

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * 每日温度
 * 根据每日气温列表，重新生成一个列表。
 * 对应位置的输出为：想要观测到更高的温度，至少需要等待的天数
 * 如果气温在这之后都不会升高，请在该位置用0代替
 */
public class DailyTemp {

    public static int[] solution(int[] temps) {
        if (ArrayUtils.isEmpty(temps)) {
            return new int[0];
        }
        if (temps.length == 1) {
            return new int[1];
        }
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temps.length];
        for (int i = 0; i < temps.length; i++) {
            if (stack.isEmpty() || temps[stack.peek()] >= temps[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty()) {
                    Integer top = stack.pop();
                    if (temps[top] >= temps[i]) {
                        stack.push(top);
                        break;
                    }
                    result[top] = i - top;
                }
                stack.push(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] temps = {73,74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(solution(temps)));
    }

}

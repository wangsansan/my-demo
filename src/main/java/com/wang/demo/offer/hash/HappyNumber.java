package com.wang.demo.offer.hash;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/24 8:10 上午
 */

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 判断一个数是不是快乐数
 * 譬如 19
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1.
 * 最终结果为1，所以19是一个快乐数
 * 题目里说了对于一个正整数要么得到1是一个快乐数，要么是一个无限循环，不是快乐数
 */
public class HappyNumber {

    /**
     * 这道题我是看了别人的刷题思路做的，比较trick
     * 这道题反向思考，如果发现有重复过的数，那就说明进行循环了，该数就不可能是一个快乐数了
     * 所以算法题，有时候不能正向思考，也可以通过不是对面来判断是这面
     * @param number
     * @return
     */
    public static boolean solution(int number) {
        Set<Integer> haveSumSet = Sets.newHashSet();
        int current = calculate(number);
        while (current != 1) {
            System.out.println(current);
            if (!haveSumSet.add(current)) {
                return false;
            }
            current = calculate(current);
        }

        return true;
    }

    private static int calculate(int number) {
        int sum = 0;
        while (number != 0) {
            int current = number % 10;
            number = number / 10;
            sum += current * current;
        }

        return sum;
    }

    public static void main(String[] args) {
        int number = 4;
        System.out.println(solution(number));
    }
}

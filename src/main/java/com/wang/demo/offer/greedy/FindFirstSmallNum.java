package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/24 8:57 上午
 */

/**
 * 给定一个非负整数N，找出小于等于N的最大的整数
 * 同时这个整数要满足其各个位数上的数字是单调递增的
 */
public class FindFirstSmallNum {

    public static int solution(Integer n) {
        char[] chars = n.toString().toCharArray();
        if (chars.length == 1) {
            if (n == 9) {
                return 9;
            } else {
                return 0;
            }
        }
        int flag = chars.length;
        for (int i = chars.length - 1; i > 0; i--) {
            int current = chars[i] - '0';
            int pre = chars[i - 1] - '0';
            if (pre > current) {
                flag = i;
                chars[i - 1]--;
            }
        }
        for (int i = flag; i < chars.length; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        int n = 332;
        System.out.println(solution(n));
    }

}

package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/18 8:23 上午
 */

import org.apache.commons.lang3.ArrayUtils;

/**
 * 找零钱
 * 顾客可以花5、10、20来买东西
 * 初始0元，判断是否可以进行找零
 */
public class ReturnCharge {

    public static boolean solution(int[] bills) {
        if (ArrayUtils.isEmpty(bills)) {
            return true;
        }

        int five = 0;
        int ten = 0;
        int twenty = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                if (five == 0) {
                    return false;
                }
                ten++;
                five--;
            } else {
                if (ten > 0) {
                    if (five > 0) {
                        ten--;
                        five--;
                        twenty++;
                    } else {
                        return false;
                    }
                } else {
                    if (five > 3) {
                        five -= 3;
                        twenty++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        System.out.println(solution(bills));
        int[] bills1 = {5, 5, 10};
        System.out.println(solution(bills1));
        int[] bills2 = {10, 10};
        System.out.println(solution(bills2));
        int[] bills3 = {5, 5, 10, 10, 20};
        System.out.println(solution(bills3));

    }

}

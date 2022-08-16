package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/16 9:03 上午
 */

/**
 * 一条环路上有n个加油站，每个加油站可以加油gas[i]
 * 去往第i+1个加油站，需要消耗cost[i]汽油
 * 求是否存在某个加油站出发，能够环岛一周
 */
public class DriveCarWithOil {

    /**
     * 暴力破解
     * @return
     */
    public static int solution(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i]) {
                continue;
            }
            int currentGas = gas[i] - cost[i];
            int start = i + 1;
            if (start >= gas.length) {
                start = 0;
            }
            while (start != i && currentGas >= 0) {
                currentGas += gas[start];
                currentGas -= cost[start];
                start++;
                if (start >= gas.length) {
                    start = 0;
                }
            }
            if (start == i && currentGas >= 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 贪心算法
     * @param gas
     * @param cost
     * @return
     */
    public static int solution1(int[] gas, int[] cost) {
        int start = 0;
        int currentSum = 0;
        int totalSum = 0;
        for (int i = 0; i < gas.length; i++) {
            currentSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (currentSum < 0) {
                start = i + 1;
                currentSum = 0;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return start;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(solution(gas, cost));
        System.out.println(solution1(gas, cost));
        int[] gas1 = {2, 3, 4};
        int[] cost1 = {3, 4, 3};
        System.out.println(solution(gas1, cost1));
        System.out.println(solution1(gas1, cost1));
    }
}

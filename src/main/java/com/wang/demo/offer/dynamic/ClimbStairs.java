package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/27 8:54 上午
 */

import java.util.Arrays;

/**
 * 746
 * 数组的每个下标作为一个阶梯，第i个阶梯对应着一个非负数的体力花费值cost[i]（下标从0开始）
 * 每当你爬上一个阶梯，你都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者两个阶梯
 * 找出达到楼层顶部的最低花费，开始时可以选择从下标0或者1的元素作为初始阶梯
 */
public class ClimbStairs {

    public static int solution(int[] cost) {
        if (cost == null || cost.length <= 2) {
            return 0;
        }

        int[] dp = new int[cost.length];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            if (i == dp.length - 1) {
                // 由于走到最后一个节点不算结束，所以如果走到了最后一个节点，需要加上最后一个节点的值做对比
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2] + cost[i]);
            } else {
                //如果没走到最后一个节点，则只需要继续往上走，不需要额外加自己的值
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            }
        }
        return dp[dp.length - 1];
    }

    public static int solution1(int[] cost) {
        if (cost == null || cost.length <= 2) {
            return 0;
        }

        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            //如果没走到最后一个节点，则只需要继续往上走，不需要额外加自己的值
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(solution(cost));
        System.out.println(solution1(cost));
        int[] cost1 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(solution(cost1));
        System.out.println(solution1(cost1));
    }

}

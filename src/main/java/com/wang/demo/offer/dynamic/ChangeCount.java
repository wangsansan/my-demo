package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/7 8:53 上午
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 给定不同面额的硬币和一个总金额。
 * 写出函数来计算可以凑成总金额的硬币组合数
 * 假设每种硬币有无限个
 */
public class ChangeCount {

    public static int solution(int[] coins, int amount) {
        // dp[i]代表总金额是i有多少种组合方式
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < dp.length; j++) {
                /**
                 * 注意此处的公式，不是dp[j] = Math.max(dp[j], dp[j-coins[i]] + 1)
                 * 因为求和的count，dp[j]不再只是和dp[j-1]相关
                 * 可以注意公式，如果最后加的不是conins[i]或者nums[i]，而是1
                 * 也就是01背包问题里的values转化成一个具体的数字，那么此时可能是写错了
                 * 需要转化成思考是不是斐波拉契数列问题
                 */
                dp[j] += dp[j - coins[i]];
                System.out.println(Arrays.toString(dp));
            }
        }

        return dp[amount];
    }

    static Stack<Integer> path = new Stack<>();
    static List<List<Integer>> result = new LinkedList<>();
    public static int solution1(int[] coins, int target) {
        if (target == 0) {
            return 1;
        }
        process(coins, 0, target);
        return result.size();
    }

    private static int process(int[] coins, int start, int target) {
        if (target == 0) {
            List<Integer> tmp = new LinkedList<>(path).stream()
                    .sorted()
                    .collect(Collectors.toList());
            if (result.contains(tmp)) {
                return 0;
            } else {
                result.add(tmp);
                return 1;
            }
        }
        if (target < 0) {
            return 0;
        }
        int count = 0;
        for (int i = start; i < coins.length; i++) {
            path.add(coins[i]);
            count += process(coins, start, target - coins[i]);
            path.pop();
        }
        return count;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 5;
        System.out.println(solution(coins, amount));
        System.out.println(solution1(coins, amount));
    }

}

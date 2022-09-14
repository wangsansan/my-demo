package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/14 8:44 上午
 */

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 和robbery那道题一样，不过加了个限制，首尾相连了
 * 因为不能偷第一家的同时偷最后一家
 */
public class Robbery2 {

    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return 0;
        }
        List<Pair<Integer, Boolean>> dp = new LinkedList<>();
        dp.add(Pair.of(nums[0], true));
        int dp1Value = Math.max(nums[0], nums[1]);
        dp.add(Pair.of(dp1Value, dp1Value != nums[1]));
        for (int i = 2; i < nums.length; i++) {
            Integer pre = dp.get(i - 1).getKey();
            Integer ppre = dp.get(i - 2).getKey();
            int tmp = ppre + nums[i];
            if (pre >= tmp) {
                dp.add(Pair.of(pre, dp.get(i - 1).getValue()));
            } else {
                if (i != nums.length - 1) {
                    /**
                     * 不是最后一家的时候，什么情况最多，就偷哪家
                     */
                    dp.add(Pair.of(tmp, dp.get(i - 2).getValue()));
                } else {
                    /**
                     * 最后一家是否偷盗，需要具体分析
                     */
                    // 没偷第一家
                    if (!dp.get(i - 2).getValue()) {
                        dp.add(Pair.of(tmp, false));
                    } else {
                        // 偷了第一家
                        dp.add(Pair.of(pre, dp.get(i - 1).getValue()));
                    }
                }
            }
        }

        return dp.get(dp.size() - 1).getKey();
    }

    public static int solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return 0;
        }
        return Math.max(doProcess(nums, 0, nums.length - 2), doProcess(nums, 1, nums.length - 1));
    }

    /**
     * 这道题的题解确实厉害，不过我上面那样也是对的
     */
    private static int doProcess(int[] nums, int start, int end) {
        int[] dp = new int[end + 1];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println(solution(nums));
        System.out.println(solution1(nums));
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(solution(nums1));
        System.out.println(solution1(nums1));
        int[] nums2 = {0};
        System.out.println(solution(nums2));
        System.out.println(solution1(nums2));
    }

}

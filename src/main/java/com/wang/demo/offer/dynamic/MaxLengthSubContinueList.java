package com.wang.demo.offer.dynamic;

import java.util.Arrays;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/24 8:31 上午
 */
public class MaxLengthSubContinueList {

    /**
     * 这道题和MaxLengthSubList对比可以发现
     * 非连续递增子序列时，dp[i]和dp[0...i-1]相关，因为非连续的最大子序列不知道出现在前置的哪一位上
     * 连续递增子序列时,dp[i]只和dp[i-1]相关
     */
    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println(solution(nums));
        int[] nums1 = {2, 2, 2, 2, 2};
        System.out.println(solution(nums1));
    }

}

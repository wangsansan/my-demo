package com.wang.demo.offer.dynamic;

import java.util.Arrays;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/24 8:31 上午
 */
public class MaxLengthSubIncrList {

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

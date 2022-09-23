package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/23 8:04 上午
 */

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * 最长递增子序列
 */
public class MaxLengthSubList {

    public static int solution(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(solution(nums));
        int[] nums1 = {0,1,0,3,2,3};
        System.out.println(solution(nums1));
        int[] nums2 = {2,2,2,2,2,2};
        System.out.println(solution(nums2));
    }

}

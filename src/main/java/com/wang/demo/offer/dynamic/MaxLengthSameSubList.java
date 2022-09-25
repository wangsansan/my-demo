package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/25 9:52 上午
 */

/**
 * 求两个数组的最长连续重复子序列长度
 */
public class MaxLengthSameSubList {

    public static int solution(int[] nums1, int[] nums2) {
        // dp[i][j]代表下标i的nums1和下标j的nums2的最长重复子序列长度
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int j = 0; j < nums2.length; j++) {
            if (nums1[0] == nums2[j]) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
            }
        }
        int max = dp[0][0];
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println(solution(nums1, nums2));
    }

}

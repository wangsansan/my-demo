package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/27 7:16 上午
 */

/**
 * 画直线，且不想交
 */
public class DrawString {

    public static int solution(int[] nums1, int nums2[]) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            if (contain(nums1, 0, 0, nums2[0])) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int j = 0; j < nums2.length; j++) {
            if (contain(nums2, 0, j, nums1[0])) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
            }
        }

        int result = dp[0][0];
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                if (dp[i][j] > result) {
                    result = dp[i][j];
                }
            }
        }

        return result;
    }

    private static boolean contain(int[] nums,int start, int end, int value) {
        for (int i = start; i <= end; i++) {
            if (nums[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static int solution1(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int result = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                if (dp[i][j] > result) {
                    result = dp[i][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 4, 2};
        int[] nums2 = {1, 2, 4};
        System.out.println(solution(nums1, nums2));
        System.out.println(solution1(nums1, nums2));
    }

}

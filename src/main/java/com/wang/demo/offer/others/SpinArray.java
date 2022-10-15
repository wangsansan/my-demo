package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/15 10:21 上午
 */

import java.util.Arrays;

/**
 * 旋转数组
 */
public class SpinArray {

    public static void solution(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        if (nums.length <= k) {
            return;
        }
        swap(nums, 0, nums.length - k - 1);
        swap(nums, nums.length - k, nums.length - 1);
        swap(nums, 0, nums.length - 1);

    }

    private static void swap(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        solution(nums, k);
        System.out.println(Arrays.toString(nums));
    }

}

package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/14 9:22 上午
 */

import java.util.Arrays;

/**
 * 给定一个数组，将0全都移动到数组后方
 */
public class SwapZero {

    public static void solution(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        int index1 = 0, index2 = 0;
        while (index2 < nums.length && index1 < nums.length) {
            while (index2 < nums.length && nums[index2] == 0) {
                index2++;
            }
            while (index1 < nums.length && nums[index1] != 0) {
                index1++;
            }
            if (index1 != index2) {
                if (index1 < nums.length && index2 < nums.length) {
                    int tmp = nums[index2];
                    nums[index2] = nums[index1];
                    nums[index1] = tmp;
                }
            }
        }
    }

    public static void solution1(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 3, 12};
        System.out.println(Arrays.toString(nums));
        solution1(nums);
        System.out.println(Arrays.toString(nums));
    }

}

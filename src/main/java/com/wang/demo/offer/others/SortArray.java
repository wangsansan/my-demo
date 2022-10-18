package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/18 7:53 上午
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组，让基数下标存基数，偶数下标存偶数
 */
public class SortArray {

    public static int[] solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{0};
        }

        int[] result = new int[nums.length];
        int index1 = 0, index2 = 1;
        for (int i = 0; i < result.length; i++) {
            if (nums[i] % 2 == 0) {
                result[index1] = nums[i];
                index1 += 2;
            } else {
                result[index2] = nums[i];
                index2 += 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 5, 7};
        System.out.println(Arrays.toString(solution(nums)));
    }

}

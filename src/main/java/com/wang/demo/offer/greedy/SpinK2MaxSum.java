package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/15 8:15 上午
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个数组和数字k，要求得到反转k次的最大值
 */
public class SpinK2MaxSum {

    public static int solution(int[] nums, int k) {
        // 1. 按照绝对值从大到小排序
        nums = Arrays.stream(nums)
                .boxed().sorted((a, b) -> Math.abs(b) - Math.abs(a))
                .mapToInt(Integer::intValue)
                .toArray();

        // 2. 从前往后遍历，把所有的负数变为正数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = Math.negateExact(nums[i]);
                k--;
                if (k == 0) {
                    break;
                }
            }
        }

        // 3. 当所有数都变为正数之后，选取最后一个数（绝对值最小的数）进行来回取反
        if ((k & 1) > 0) {
            int lastIndex = nums.length - 1;
            nums[lastIndex] = Math.negateExact(nums[lastIndex]);
        }

        return Arrays.stream(nums).reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 3};
        int k = 1;
        System.out.println(solution(nums, k));
        int[] nums1 = {3, -1, 0, 2};
        k = 3;
        System.out.println(solution(nums1, k));
        int[] nums2 = {2, -3, -1, 5, -4};
        k = 2;
        System.out.println(solution(nums2, k));
    }

}

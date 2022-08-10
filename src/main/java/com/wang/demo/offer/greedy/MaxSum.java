package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/10 9:00 上午
 */

import org.apache.commons.lang3.ArrayUtils;

/**
 * 给定一个整数数组，找到一个具有最大和的连续子数组，并返回其最大值
 */
public class MaxSum {

    public static int solution(int[] nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return 0;
        }
        int currentMax = nums[0];
        int max = nums[0];
        int index = 1;
        while (index < nums.length){
            currentMax += nums[index];
            if (currentMax < 0) {
                currentMax = 0;
            } else {
                if (currentMax > max) {
                    max = currentMax;
                }
            }
            index++;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution(nums));
    }

}

package com.wang.demo.offer.continueStack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/8 8:34 上午
 */

import java.util.Stack;

/**
 * 接雨水
 */
public class HoldRain {

    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 0;
        }
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                result[stack.pop()] = i;
            }
            stack.push(i);
        }
        int realResult = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > 0) {
                int height = nums[i];
                int width = result[i] - i - 1;
                int area = height * width;
                if (area <= 0) {
                    i = result[i] - 1;
                    continue;
                }
                for (int j = i + 1; j < result[i]; j++) {
                    area -= nums[j];
                }
                realResult += area;
                i = result[i] - 1;
            }
        }

        return realResult;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution(nums));
        int[] nums1 = {4, 2, 0, 3, 2, 5};
        System.out.println(solution(nums1));
    }

}

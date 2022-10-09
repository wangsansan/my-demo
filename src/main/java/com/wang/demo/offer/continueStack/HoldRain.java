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

    public static int solution1(int[] nums) {
        int[] maxLeft = new int[nums.length];
        int[] maxRight = new int[nums.length];
        maxLeft[0] = nums[0];
        maxRight[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], nums[i]);
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], nums[i]);
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int height = Math.min(maxLeft[i], maxRight[i]);
            result += (height - nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution(nums));
        System.out.println(solution1(nums));
        int[] nums1 = {4, 2, 0, 3, 2, 5};
        System.out.println(solution(nums1));
        System.out.println(solution1(nums1));
    }

}

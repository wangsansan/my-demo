package com.wang.demo.offer8.array;

import java.util.Arrays;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/6 18:58
 */
public class ArrayPingfang977 {

    public static int[] solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        if (nums.length == 1) {
            return new int[]{nums[0] * nums[0]};
        }
        int[] result = new int[nums.length];
        int start = 0, end = nums.length - 1, index = nums.length - 1;
        while (start <= end) {
            if (Math.abs(nums[start]) > Math.abs(nums[end])) {
                result[index--] = nums[start] * nums[start];
                start++;
            } else {
                result[index--] = nums[end] * nums[end];
                end--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{-4,-1,0,3,10})));
        System.out.println(Arrays.toString(solution(new int[]{-7,-3,2,3,11})));
    }

}

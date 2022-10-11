package com.wang.demo.offer.continueStack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/9 9:03 上午
 */
public class MaxArea {

    public static int solution(int[] nums) {
        // 左边第一个小于nums[i]的下标
        int[] minLeft = new int[nums.length];
        // 右边第一个小于nums[i]的下标
        int[] minRight = new int[nums.length];
        minLeft[0] = -1;
        minRight[nums.length - 1] = nums.length;
        for (int i = 1; i < nums.length; i++) {
            int t = i - 1;
            /**
             * 此处之所以需要增加一个循环，而接雨水不需要，是因为：
             * 接雨水找的是0..i-1最大的数；而本题找的是i-1...0中第一个比i小的数
             * 也就是这个数和nums[i]的取值相关了，所以minLeft[i-1]对minLeft[i]没有了参考意义
             */
            while (t >= 0 && nums[t] >= nums[i]) {
                t = minLeft[t];
            }
            minLeft[i] = t;
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            int t = i + 1;
            while (t < nums.length && nums[t] >= nums[i]) {
                t = minRight[t];
            }
            minRight[i] = t;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i] * (minRight[i] - minLeft[i] - 1);
            result = Math.max(sum, result);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 6, 2, 3};
        System.out.println(solution(nums));
    }

}

package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/13 8:53 上午
 */

import org.apache.commons.lang3.ArrayUtils;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度
 * 判断你能否到达最后一个位置
 */
public class JumpToEnd {

    public static boolean solution(int[] nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return true;
        }
        if (nums.length == 1) {
            return true;
        }
        int cover = 0;
        // 注意此处的 i 的取值范围
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(i + nums[i], cover);
            if (cover >= nums.length - 1) {
                return  true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(solution(nums));
    }

}

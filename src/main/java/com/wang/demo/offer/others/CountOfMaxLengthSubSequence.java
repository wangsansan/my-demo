package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/3 8:08 上午
 */

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 * 673.最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 *
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 */
public class CountOfMaxLengthSubSequence {

    public static int solution(int[] nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        // dp[i]表示下标0到下标i，得到的最长子序列数值
        int[] dp = new int[nums.length];
        // count[i]表示下标i的最长子序列值时的个数
        int[] count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            count[i] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    int org = dp[i];
                    int compare = dp[j] + 1;
                    if (org == compare) {
                        // 此处要注意
                        count[i] = count[j] + count[i];
                    } else if (org < compare) {
                        dp[i] = compare;
                        count[i] = count[j];
                    }
                }
            }
        }

        // 1. 求出最长子序列长度
        int maxValue = Arrays.stream(dp).max().getAsInt();
        // 2. 求出最长子序长度的所有下标
        int result = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxValue) {
                result += count[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //nums  1, 1, 2, 2, 9, 8, 7, 11，11
        //dp    1, 1, 2, 2, 3, 3, 3, 4， 4
        //count 1, 1, 2, 2, 4, 4, 4, 12，12
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println(solution(nums));
        int[] nums1 = {2, 2, 2, 2, 2};
        System.out.println(solution(nums1));
    }

}

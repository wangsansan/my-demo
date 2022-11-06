package com.wang.demo.offer.others;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/6 9:31 上午
 */

/**
 * 31.下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * #
 */
public class NextBigNumber {

    public static int[] solution(int[] numbers) {
        if (Objects.isNull(numbers) || numbers.length <= 1) {
            return numbers;
        }

        for (int i = numbers.length - 1; i >= 0; i--) {
            for (int j = numbers.length - 1; j > i; j--) {
                if (numbers[i] < numbers[j]) {
                    ArrayUtils.swap(numbers, i, j);
                    Arrays.sort(numbers, i + 1, numbers.length);
                    return numbers;
                }
            }
        }

        ArrayUtils.reverse(numbers);
        return numbers;
    }

    public static void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    // 交换
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    // [i + 1, nums.length) 内元素升序排序
                    Arrays.sort(nums, i + 1, nums.length);
                    return;
                }
            }
        }
        Arrays.sort(nums); // 不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 2};
        System.out.println(Arrays.toString(solution(numbers)));
//        nextPermutation(numbers);
//        System.out.println(Arrays.toString(numbers));
    }

}

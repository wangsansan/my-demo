package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/11 8:33 上午
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组nums，输出当前数组中比nums[i]小的数的个数
 */
public class CountOfSmaller {

    public static int[] solution(int[] nums) {
        int[] result = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        int[] temp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(temp);
        for (int i = 0; i < temp.length; i++) {
            map.putIfAbsent(temp[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = map.getOrDefault(nums[i], 0);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {8, 1, 2, 2, 3};
        System.out.println(Arrays.toString(solution(nums)));
    }

}

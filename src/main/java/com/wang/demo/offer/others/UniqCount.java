package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/13 8:45 上午
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 判断数组里的数是不是出现了唯一一次
 */
public class UniqCount {

    public static boolean solution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer value = map.getOrDefault(nums[i], 0);
            map.put(nums[i], value + 1);
        }
        Set<Integer> countSet = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!countSet.add(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 1, 1, 3};
        System.out.println(solution(nums));
        int[] nums1 = {1, 2};
        System.out.println(solution(nums1));
    }

}

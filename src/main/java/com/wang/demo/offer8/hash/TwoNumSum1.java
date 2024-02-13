package com.wang.demo.offer8.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/9 15:53
 */
public class TwoNumSum1 {

    public static int[] solution(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if (indexMap.containsKey(left)) {
                return new int[] {indexMap.get(left), i};
            } else {
                indexMap.put(nums[i], i);
            }
        }

        return new int[] {-1, -1};
    }

}

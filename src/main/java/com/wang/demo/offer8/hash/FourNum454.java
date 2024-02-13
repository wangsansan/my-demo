package com.wang.demo.offer8.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/9 17:02
 */
public class FourNum454 {

    public static int solution(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = processSum(nums1, nums2);
        int result = 0;
        for (int i : nums3) {
            for (int j : nums4) {
                result += map1.getOrDefault(-i-j, 0);
            }
        }

        return result;
    }

    private static Map<Integer, Integer> processSum(int[] nums1, int[] nums2) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i : nums1) {
            for (int i1 : nums2) {
                int value = i + i1;
                result.put(value, result.getOrDefault(value, 0) + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
    }

}

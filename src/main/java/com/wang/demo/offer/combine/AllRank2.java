package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/7 8:18 上午
 */

import java.util.*;

/**
 * 给定一个可包含重复数字的序列nums，按任意顺序 返回所有不重复则全排列
 */
public class AllRank2 {

    private static List<List<Integer>> result = new LinkedList<>();

    private static Stack<Integer> path = new Stack<>();

    private static Stack<Integer> usedIndex = new Stack<>();

    public static void solution(int[] nums) {
        process(nums);
    }

    private static void process(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new LinkedList<>(path));
            return;
        }

        Set<Integer> sameLayerUsed = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 因为元数据中的数字可能重复，所以纵向必须通过下标来去重
            if (usedIndex.contains(i)) {
                continue;
            }
            // 因为返回的结果集里也不允许有重复的，同时元数据又不是一个sorted的数组，所以同一层需要记录已使用过的数字，来进行去重
            if (i > 0) {
                if (sameLayerUsed.contains(nums[i])) {
                    continue;
                }
            }
            sameLayerUsed.add(nums[i]);
            path.push(nums[i]);
            usedIndex.push(i);
            process(nums);
            path.pop();
            usedIndex.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        solution(nums);
        System.out.println(result);
        result.clear();
        path.clear();
        usedIndex.clear();
        int[] nums1 = {1, 2, 3};
        solution(nums1);
        System.out.println(result);
    }
}

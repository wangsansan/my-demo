package com.wang.demo.offer.combine;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/7 8:11 上午
 */

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列
 */
public class AllRank {

    private static List<List> result = new LinkedList<>();

    private static Stack<Integer> path = new Stack<>();

    public static void solution(int[] nums) {
        process(nums);
    }

    private static void process(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new LinkedList(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 因为元数据中的数字不重复，所以使用这个判断就可以判断出纵向的数字是否被使用过
            if (path.contains(nums[i])) {
                continue;
            }
            path.push(nums[i]);
            process(nums);
            path.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        solution(nums);
        System.out.println(result);
    }

}

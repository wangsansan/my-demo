package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/31 9:30 上午
 */

import java.util.*;

/**
 * 找出所有相加和为target的k个数的组合
 * 组合里的数从candidates里选择，不可以重复
 * 不可以重复这个逻辑，使用剪枝的方法实现：同一层的相同节点，如果已经处理过，则跳过该节点
 */
public class FindSum4 {

    private static List<List<Integer>> result = new LinkedList<>();
    private static Stack<Integer> path = new Stack<>();
    private static int sum = 0;

    public static void solution(int[] candidates, int target) {
        // 排序是为了优化，在后面不需要从头开始查找，同时也可以把[2,3,3]和[3,2,3]给去重
        candidates = Arrays.stream(candidates).sorted().toArray();
        process(candidates, target, 0);
    }

    private static void process(int[] candidates, int target, int index) {
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        } else if (sum > target) {
            return;
        }
        /**
         * 每次遍历的循环，都可以理解为是同一层
         * 也就是组合剪枝的同一层
         */
        for (int i = index; i < candidates.length; i++) {
            /**
             * 如果一个数在同一层已经处理过，跳过
             * 注意看这题的解法，FindSum3里为了去重，使用Set，本题使用剪枝，使得根本不用去重
             */
            if (i > index && candidates[i] == candidates[i-1]) {
                continue;
            }
            path.push(candidates[i]);
            sum += candidates[i];
            process(candidates, target, i + 1);
            path.pop();
            sum -= candidates[i];
        }

    }


    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        solution(candidates, target);
        System.out.println(result);
        result.clear();
        path.clear();
        sum = 0;
        int[] candidates1 = {2, 5, 2, 1, 2};
        int target1 = 5;
        solution(candidates1, target1);
        System.out.println(result);
    }

}

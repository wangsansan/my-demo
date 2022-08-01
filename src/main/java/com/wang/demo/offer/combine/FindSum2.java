package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/31 9:30 上午
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 找出所有相加和为target的k个数的组合
 * 组合里的数从candidates里选择，可以重复
 */
public class FindSum2 {

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
            List<Integer> temp = new LinkedList<>(path);
            result.add(temp);
            return;
        } else if (sum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target - sum) {
                break;
            }
            path.push(candidates[i]);
            sum += candidates[i];
            process(candidates, target, i);
            path.pop();
            sum -= candidates[i];
        }

    }


    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        solution(candidates, target);
        System.out.println(result);
        result.clear();
        path.clear();
        sum = 0;
        int[] candidates1 = {2, 5, 3};
        int target1 = 8;
        solution(candidates1, target1);
        System.out.println(result);
    }

}

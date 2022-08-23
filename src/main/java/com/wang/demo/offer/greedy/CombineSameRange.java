package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/23 9:09 上午
 */

import javafx.util.Pair;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 合并区间
 */
public class CombineSameRange {

    private static List<Pair<Integer, Integer>> result = new LinkedList<>();

    public static void solution(List<Pair<Integer, Integer>> nums) {
        if (CollectionUtils.isEmpty(nums)) {
            return;
        }
        if (nums.size() == 1) {
            result = nums;
            return;
        }
        List<Pair<Integer, Integer>> pairList = nums.stream()
                .sorted(Comparator.comparing(Pair::getKey))
                .collect(Collectors.toList());
        Pair<Integer, Integer> value = pairList.get(0);
        Pair<Integer, Integer> currentValue = new Pair<>(value.getKey(), value.getValue());
        int currentEnd = value.getValue();
        for (int i = 1; i < pairList.size(); i++) {
            Pair<Integer, Integer> current = pairList.get(i);
            if (current.getKey() <= currentEnd) {
                if (current.getValue() > currentEnd) {
                    currentValue = new Pair<>(currentValue.getKey(), current.getValue());
                    currentEnd = currentValue.getValue();
                }
            } else {
                result.add(new Pair<>(currentValue.getKey(), currentValue.getValue()));
                currentValue = current;
                currentEnd = currentValue.getValue();
            }
        }
        result.add(new Pair<>(currentValue.getKey(), currentValue.getValue()));
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> param = new LinkedList<>();
        param.add(new Pair<>(1, 3));
        param.add(new Pair<>(2, 6));
        param.add(new Pair<>(8, 10));
        param.add(new Pair<>(15, 18));
        solution(param);
        System.out.println(result);
        param.clear();
        result.clear();
        param.add(new Pair<>(1, 4));
        param.add(new Pair<>(4, 5));
        solution(param);
        System.out.println(result);
    }

}

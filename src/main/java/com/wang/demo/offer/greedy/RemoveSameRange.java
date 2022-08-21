package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/21 8:51 上午
 */

import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 删除重叠空间，使得数组无重叠
 * 判断需要删除几个
 */
public class RemoveSameRange {

    public static int solution(List<Pair<Integer, Integer>> nums) {
        if (CollectionUtils.size(nums) <= 1) {
            return 0;
        }

        List<Pair<Integer, Integer>> numList = nums.stream()
                .sorted(Comparator.comparing(Pair::getKey))
                .collect(Collectors.toList());
        int maxEnd = numList.get(0).getValue();
        // 计算不重叠个数
        int count = 1;
        for (int i = 1; i < numList.size(); i++) {
            Pair<Integer, Integer> current = numList.get(i);
            if (current.getKey() >= maxEnd) {
                count++;
                maxEnd = current.getValue();
            }
        }

        return numList.size() - count;
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> param = new LinkedList<>();
        param.add(new Pair<>(1, 2));
        param.add(new Pair<>(2, 3));
        param.add(new Pair<>(3, 4));
        param.add(new Pair<>(1, 3));
        System.out.println(param);
        System.out.println(solution(param));
        param.clear();
        param.add(new Pair<>(1, 2));
        param.add(new Pair<>(1, 2));
        param.add(new Pair<>(1, 2));
        System.out.println(param);
        System.out.println(solution(param));
        param.clear();
        param.add(new Pair<>(1, 2));
        param.add(new Pair<>(2, 3));
        System.out.println(param);
        System.out.println(solution(param));
    }

}

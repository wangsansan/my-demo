package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/19 8:26 上午
 */

import javafx.util.Pair;

import java.util.*;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组people表示队列中的一些人的属性
 * 每个people[i] = [hi,ki]，表示第i个人的身高为hi，前面正好有ki个身高大于等于自己的人
 */
public class SortHeight {

    public static List<Pair<Integer, Integer>> solution(List<Pair<Integer, Integer>> people) {
        Map<Integer, Pair<Integer, Integer>> tmpMap = new HashMap<>();
        people.sort(SortHeight::comp);
        for (int i = 0; i < people.size(); i++) {
            System.out.println(tmpMap);
            Pair<Integer, Integer> pair = people.get(i);
            processPairIndex(people.get(i), pair.getValue(), tmpMap);
        }
        List<Pair<Integer, Integer>> result = new LinkedList<>();
        for (int i = 0; i < people.size(); i++) {
            result.add(tmpMap.get(i));
        }

        return result;
    }

    public static List<Pair<Integer, Integer>> solution1(List<Pair<Integer, Integer>> people) {
        people.sort(SortHeight::comp);
        List<Pair<Integer, Integer>> result = new ArrayList<>(people.size());
        // 这个地方需要初始化，就很坑爹
        for (int i = 0; i < people.size(); i++) {
            result.add(null);
        }
        for (int i = 0; i < people.size(); i++) {
            System.out.println(result);
            Pair<Integer, Integer> pair = people.get(i);
            processPairIndex1(people.get(i), pair.getValue(), result);
        }
        return result;
    }

    private static void processPairIndex(Pair<Integer, Integer> pair, int index, Map<Integer, Pair<Integer, Integer>> tmpMap) {
        Pair<Integer, Integer> oldPair = tmpMap.get(index);
        if (Objects.isNull(oldPair)) {
            tmpMap.put(index, pair);
        } else {
            // 此处插入排序的判断条件很重要
            if (oldPair.getValue() > pair.getValue() || oldPair.getKey() > pair.getKey()) {
                processPairIndex(oldPair, index + 1, tmpMap);
                tmpMap.put(index, pair);
            } else {
                processPairIndex(pair, index + 1, tmpMap);
            }
        }
    }

    private static void processPairIndex1(Pair<Integer, Integer> pair, int index, List<Pair<Integer, Integer>> tmpList) {
        Pair<Integer, Integer> oldPair = tmpList.get(index);
        if (Objects.isNull(oldPair)) {
            tmpList.set(index, pair);
        } else {
//            Pair<Integer, Integer> oldPair = tmpList.get(index);
            // 此处插入排序的判断条件很重要
            if (oldPair.getValue() > pair.getValue() || oldPair.getKey() > pair.getKey()) {
                processPairIndex1(oldPair, index + 1, tmpList);
                tmpList.set(index, pair);
            } else {
                processPairIndex1(pair, index + 1, tmpList);
            }
        }
    }

    private static int comp(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        if (!a.getKey().equals(b.getKey())) {
            return b.getKey() - a.getKey();
        }

        return a.getValue() - b.getValue();
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> param = new LinkedList<>();
        param.add(new Pair<>(7, 0));
        param.add(new Pair<>(4, 4));
        param.add(new Pair<>(7, 1));
        param.add(new Pair<>(5, 0));
        param.add(new Pair<>(6, 1));
        param.add(new Pair<>(5, 2));
        System.out.println(param);
        System.out.println(solution1(param));
    }

}

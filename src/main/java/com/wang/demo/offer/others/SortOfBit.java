package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/8 8:14 上午
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1356. 根据数字二进制下 1 的数目排序
 * 题目链接：https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits/
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 */
public class SortOfBit {

    public static List<Integer> solution(List<Integer> valueList) {
        return valueList.stream()
                .sorted(SortOfBit::compare)
                .collect(Collectors.toList());
    }

    private static int compare(int a, int b) {
        int aCount = bitCount(a);
        int bCount = bitCount(b);
        if (aCount == bCount) {
            return a - b;
        }
        return aCount - bCount;
    }

    private static int bitCount(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1); // 清除最低位的1
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> valueList = Arrays.asList(0,1,2,3,4,5,6,7,8);
        System.out.println(solution(valueList));
    }

}

package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/11 7:43 上午
 */

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度
 * 通过从原始序列中删除一些（也可以不删除）来获得子序列
 */
public class LongSwing {

    public static List<Integer> solution(Integer[] nums) {
        if (ArrayUtils.isEmpty(nums) || nums.length == 1) {
            return Collections.emptyList();
        }

        List<Integer> result = new LinkedList(Arrays.asList(nums));
        int pre = 0;
        int index = 1;
        while (index < result.size()) {
            int currentGap = result.get(index) - result.get(index - 1);
            if (currentGap == 0 && index != 1) {
                result.remove(index);
                index--;
            }
            if ( currentGap > 0 && pre > 0 ) {
                result.remove(index);
                index--;
            } else if (currentGap < 0 && pre < 0){
                result.remove(index);
                index--;
            }
            pre = currentGap;
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println(Arrays.asList(nums));
        System.out.println(solution(nums));
        Integer[] nums1 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println(solution(nums1));
        Integer[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(solution(nums2));
    }

}

package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/17 8:28 上午
 */

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * 给n个孩子分糖果
 * 每个孩子至少一个，相邻的孩子，评分高的必须多于低的
 */
public class SplitCandy {

    public static int solution(int[] rating) {
        if (ArrayUtils.isEmpty(rating)) {
            return 0;
        }
        if (rating.length == 1) {
            return 1;
        }
        int[] candy = new int[rating.length];
        candy[0] = 1;
        //1. 从左向右遍历，确保当右边孩子比左边孩子得分高的时候，得到的糖比左边孩子多
        for (int i = 1; i < rating.length; i++) {
            if (rating[i] > rating[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            } else {
                candy[i] = 1;
            }
        }

        //2. 从右向左遍历，确保当左边孩子比右边孩子得分高的时候，得到的糖比右边孩子高
        for (int i = rating.length - 2; i >= 0; i--) {
            if (rating[i] > rating[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }

        return Arrays.stream(candy).sum();
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 2};
        System.out.println(solution(nums));
        int[] nums1 = {1, 2, 2};
        System.out.println(solution(nums1));
        int[] nums2 = {1, 2, 2, 5, 4, 3, 2};
        System.out.println(solution(nums2));
    }
}

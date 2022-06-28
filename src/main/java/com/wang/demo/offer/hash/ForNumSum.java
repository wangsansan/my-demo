package com.wang.demo.offer.hash;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/27 9:11 上午
 */
public class ForNumSum {

    public static void solution(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return;
        }

        Set<List<Integer>> result = Sets.newHashSet();

        Arrays.sort(nums);

        for (int i = 0; i <= nums.length - 4; i++) {
            for (int j = i + 1; j < nums.length -3; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        if (result.add(Lists.newArrayList(nums[i], nums[j], nums[left], nums[right]))) {
                            System.out.printf("%d,%d, %d,%d%n", nums[i], nums[j], nums[left], nums[right]);
                        }
                        right--;
                        left++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {-1,0,1,2,-1,-4};
//        int[] array = {0,0,0,0};
        solution(array, -5);
    }

}

package com.wang.demo.offer8.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/12 16:44
 */
public class ThreeNumSum15 {

    public static List<List<Integer>> solution(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                return result;
            }
            int index1 = i + 1;
            int index2 = nums.length - 1;
            boolean updateIndex1 = false, updateIndex2 = false;
            while (index1 < index2) {
                updateIndex1 = false;
                updateIndex2 = false;
                int value = nums[index1] + nums[index2];
                if (value == -nums[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[index1]);
                    list.add(nums[index2]);
                    result.add(list);
                    index1++;
                    index2--;
                    updateIndex1 = true;
                    updateIndex2 = true;
                } else if (value > -nums[i]) {
                    index2--;
                    updateIndex2 = true;
                } else {
                    index1++;
                    updateIndex1 = true;
                }
                if (updateIndex2) {
                    while (index2 >= 0 && nums[index2] == nums[index2 + 1]) {
                        index2--;
                    }
                }
                if (updateIndex1) {
                    while (index1 < nums.length && nums[index1] == nums[index1 - 1]) {
                        index1++;
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> solution1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                return result;
            }
            int index1 = i + 1;
            int index2 = nums.length - 1;
            while (index1 < index2) {
                int value = nums[index1] + nums[index2];
                if (value == -nums[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[index1]);
                    list.add(nums[index2]);
                    result.add(list);
                    index1++;
                    index2--;
                    while (index2 >= 0 && nums[index2] == nums[index2 + 1]) {
                        index2--;
                    }
                    while (index1 < nums.length && nums[index1] == nums[index1 - 1]) {
                        index1++;
                    }
                } else if (value > -nums[i]) {
                    index2--;
                } else {
                    index1++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-2, 0, 0, 2, 2}));
    }

}

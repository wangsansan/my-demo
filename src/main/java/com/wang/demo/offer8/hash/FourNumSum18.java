package com.wang.demo.offer8.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/13 14:59
 */
public class FourNumSum18 {

    /**
     * 题目还是挺难的，还得注意考虑溢出的场景；
     * 在实际生产环境代码，咱们还是用Set比较合理。
     */
    public static List<List<Integer>> solution(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] > target) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                int value1 = nums[i] + nums[j];
                if (value1 > 0 && value1 > target) {
                    continue;
                }
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int index1 = j + 1, index2 = nums.length - 1;
                while (index1 < index2) {
                    int value =  value1 + nums[index1] + nums[index2];
                    if (value ==  target) {
                        List<Integer> valueList = new ArrayList<>(4);
                        valueList.add(nums[i]);
                        valueList.add(nums[j]);
                        valueList.add(nums[index1]);
                        valueList.add(nums[index2]);
                        result.add(valueList);
                        index1++;
                        index2--;
                        while (index1 < nums.length - 1 && nums[index1] == nums[index1 - 1]) {
                            index1++;
                        }
                        while (index2 > j && nums[index2] == nums[index2 + 1]) {
                            index2--;
                        }
                    } else if (value < target) {
                        index1++;
                    } else {
                        index2--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296));
    }

}

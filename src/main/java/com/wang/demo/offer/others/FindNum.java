package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/17 8:38 上午
 */

import netscape.security.UserTarget;

import java.util.Arrays;

/**
 * 从递增序列中找到某个target
 */
public class FindNum {

    public static int[] solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return new int[]{-1, -1};
        }

        int start = processSmaller(nums, 0, nums.length, target);
        int end = processBigger(nums, 0, nums.length - 1, target);
        return new int[]{start, end};

    }

    private static int processBigger(int[] nums, int start, int end, int target) {
        int mid = start / 2 + end / 2;
        if (start == end) {
            if (nums[start] == target) {
                return end;
            } else if (nums[start - 1] == target) {
                return start - 1;
            }
        }
        if (nums[mid] > target && nums[mid - 1] == target) {
            return mid - 1;
        } else if (nums[mid] > target) {
            return processBigger(nums, start, mid - 1, target);
        } else {
            return processBigger(nums, mid + 1, end, target);
        }

    }

    private static int processSmaller(int[] nums, int start, int end, int target) {
        int mid = start / 2 + end / 2;
        if (start == end) {
            if (nums[start] == target) {
                return end;
            } else if (nums[start + 1] == target) {
                return start + 1;
            }
        }
        if (nums[mid] < target && nums[mid + 1] == target) {
            return mid + 1;
        } else if (nums[mid] >= target) {
            return processSmaller(nums, start, mid - 1, target);
        } else {
            return processSmaller(nums, mid + 1, end, target);
        }

    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(solution(nums, target)));
    }

}

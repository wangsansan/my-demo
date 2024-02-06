package com.wang.demo.offer8.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/6 17:52
 */
public class BinarySearch704 {

    public static int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return process(nums, 0, nums.length - 1, target);
    }

    private static int process(int[] nums, int index1, int index2, int target) {
        if (index1 < 0 || index2 > nums.length - 1 || index2 < index1) {
            return -1;
        }
        if (index1 == index2) {
            if (nums[index1] == target) {
                return index1;
            }
            return -1;
        }
        if (target < nums[index1] || target > nums[index2]) {
            return -1;
        }

        int mid = index1 / 2 + index2 / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return process(nums, index1, mid - 1, target);
        } else {
            return process(nums, mid + 1, index2, target);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        System.out.println(solution(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }
}

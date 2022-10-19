package com.wang.demo.offer.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/19 8:56 上午
 */
public class FindIndex {

    public static int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (target < nums[0]) {
            return 0;
        }

        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int start = 0, end = nums.length - 1;
        return process(nums, start, end, target);
    }

    private static int process(int[] nums, int start, int end, int target) {
        int mid = start / 2 + end / 2;
        if (nums[mid] == target) {
            return mid;
        } else {
            if ((mid > 0 && target >= nums[mid - 1]) && (target <= nums[mid])) {
                return mid;
            } else if (nums[mid] > target) {
                return process(nums, start, mid - 1, target);
            } else {
                return process(nums, mid + 1, end, target);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(solution(nums, 5));
        System.out.println(solution(nums, 2));
        System.out.println(solution(nums, 7));
        System.out.println(solution(nums, 0));
    }

}

package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/12 8:52 上午
 */
public class MountainArray {

    public static boolean solution(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        boolean direct = true;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            boolean currentDirect = true;
            if (nums[i] == pre) {
                return false;
            } else if (nums[i] < pre) {
                currentDirect = false;
            }
            if (direct && !currentDirect) {
                direct = false;
            } else {
                if (currentDirect != direct) {
                    return false;
                }
            }
            pre = nums[i];
        }

        if (direct) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1};
        System.out.println(solution(nums));
        int[] nums1 = {3, 5, 5};
        System.out.println(solution(nums1));
        int[] nums2 = {0, 3, 2, 1};
        System.out.println(solution(nums2));
    }

}

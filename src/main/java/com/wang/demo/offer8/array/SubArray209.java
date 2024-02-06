package com.wang.demo.offer8.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/6 19:08
 */
public class SubArray209 {

    public static int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0, current = 0, index1 = 0, index2 = 0;
        int tempResult = nums[0];
        while (index2 < nums.length) {
            if (tempResult >= target) {
                current = index2 - index1 + 1;
                if (result == 0) {
                    result = current;
                } else {
                    result = Math.min(result, current);
                }
                tempResult -= nums[index1];
                index1++;
            } else {
                index2++;
                if (index2 == nums.length) {
                    break;
                }
                tempResult += nums[index2];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2,3,1,2,4,3}, 7));
        System.out.println(solution(new int[]{1,1,1,1}, 5));
    }

}

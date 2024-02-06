package com.wang.demo.offer8.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/6 18:47
 */
public class RemoveItem27 {

    public static int solution(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index1 = 0, index2 = 0;
        while (index2 < nums.length) {
            if (nums[index2] != val) {
                nums[index1++] = nums[index2];
            }
            index2++;
        }
        return index1;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,2,2,3}, 3));
        System.out.println(solution(new int[]{0,1,2,2,3,0,4,2}, 2));
    }

}

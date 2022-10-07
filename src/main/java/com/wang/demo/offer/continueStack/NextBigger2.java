package com.wang.demo.offer.continueStack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/7 7:46 上午
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 循环数组，查找下一个更大的数
 */
public class NextBigger2 {

    public static int[] solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            int[] result = new int[1];
            result[0] = -1;
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        Set<Integer> added = new HashSet<>(nums.length);
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                Integer peek = stack.peek();
                if (nums[peek] >= nums[i]) {
                    if (peek == i) {
                        // 此处是否正确，其实待思量。还是solution2的解法靠谱
                        break;
                    }else {
                        if (!added.contains(i)) {
                            stack.push(i);
                            added.add(i);
                        }
                    }
                } else {
                    while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                        Integer top = stack.pop();
                        result[top] = nums[i];
                    }
                    if (!added.contains(i)) {
                        stack.push(i);
                        added.add(i);
                    }
                }
            }
            if (i == nums.length - 1) {
                i = 0;
            }
        }

        return result;
    }

    public static int[] solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return result;
    }

    public static int[] solution2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % nums.length]) {
                result[stack.pop()] = nums[i % nums.length];
            }
            stack.push(i % nums.length);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        System.out.println(Arrays.toString(solution(nums)));
        System.out.println(Arrays.toString(solution1(nums)));
        System.out.println(Arrays.toString(solution2(nums)));
    }

}

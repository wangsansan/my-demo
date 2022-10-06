package com.wang.demo.offer.continueStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/6 9:51 上午
 */
public class NextBigger {

    /**
     * 最近做题做得有点思维定势了
     * 昨天做了单调栈的第一题之后，这道题光想着照着原题来搞
     * 全然没想到中间的映射可以通过map来搞，减少时间复杂度
     * 昨天那么做，是因为result的顺序有意义，
     * 今天这道题里，昨天的result变成了一个中间结果，顺序没有了任何意义
     * 今天这道题里，是nums1的顺序有意义！！！！
     * 除了相同的题，我们是否能够做到举一反三???
     */
    public static int[] solution(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums2.length);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            if (stack.isEmpty() || nums2[stack.peek()] >= nums2[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    Integer top = stack.pop();
                    map.put(nums2[top], nums2[i]);
                }
                stack.push(i);
            }
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(solution(nums1, nums2)));
    }

}

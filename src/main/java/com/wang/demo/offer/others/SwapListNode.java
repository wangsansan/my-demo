package com.wang.demo.offer.others;

import com.wang.demo.offer.list.ListNode;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/20 8:22 上午
 */
public class SwapListNode {

    public static int[] solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        if (nums.length == 1) {
            return nums;
        }

        ListNode head = ListNode.generateList(nums);
        ListNode pre = null;
        ListNode node1 = head;
        ListNode node2 = node1.getNextNode();
        while (Objects.nonNull(node1) && Objects.nonNull(node2)) {
            ListNode nextNode = node2.getNextNode();
            node1.setNextNode(nextNode);
            node2.setNextNode(node1);
            if (Objects.nonNull(pre)) {
                pre.setNextNode(node2);
            } else {
                head = node2;
            }
            pre = node1;
            node1 = nextNode;
            if (Objects.nonNull(node1)) {
                node2 = node1.getNextNode();
            }
        }

        return buildValue(head, nums.length);
    }

    private static int[] buildValue(ListNode root, int size) {
        int[] result = new int[size];
        ListNode node = root;
        int index = 0;
        while (Objects.nonNull(node)) {
            result[index++] = node.getValue();
            node = node.getNextNode();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(solution(nums)));
        int[] nums1 = {};
        System.out.println(Arrays.toString(solution(nums1)));
        int[] nums2 = {1};
        System.out.println(Arrays.toString(solution(nums2)));
    }

}

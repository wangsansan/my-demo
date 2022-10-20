package com.wang.demo.offer.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/18 1:05 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListNode {

    private int value;

    private ListNode nextNode;

    public static ListNode generateList(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode head = new ListNode(nums[0], null);
        ListNode pre = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i], null);
            pre.setNextNode(node);
            pre = node;
        }
        return head;
    }

}

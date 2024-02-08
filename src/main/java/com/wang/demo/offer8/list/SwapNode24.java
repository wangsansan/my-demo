package com.wang.demo.offer8.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/7 13:33
 */
public class SwapNode24 extends BaseList {

    public static ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode result = null;
        while (node1 != null && node2 != null) {
            ListNode next = node2.next;
            if (pre != null) {
                pre.next = node2;
            } else {
                result = node2;
            }
            node2.next = node1;
            node1.next = next;
            pre = node1;
            node1 = next;
            if (node1 != null) {
                node2 = node1.next;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        solution(node1);
    }

}

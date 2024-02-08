package com.wang.demo.offer8.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/7 08:28
 */
public class RemoveItem203 extends BaseList {

    public ListNode solution(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode newHead = head;
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            if (node.val == val) {
                if (pre == null) {
                    // 头结点要被删除的情况
                    newHead = node.next;
                    node = node.next;
                } else {
                    pre.next = node.next;
                    node.next = null;
                    node = pre.next;
                }
            } else {
                pre = node;
                node = pre.next;
            }
        }
        return newHead;
    }

    public ListNode solution1(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode node = head.next;
        while (node != null) {
            if (node.val == val) {
                pre.next = node.next;
            } else {
                pre = node;
            }
            node = pre.next;
        }
        return head;
    }

    /**
     * 凡是头结点可能被删除的情况，都可以考虑mock一个头结点
     */
    public static ListNode solution2(ListNode head, int val) {
        ListNode mock = new ListNode(0);
        mock.next = head;
        ListNode node = head;
        ListNode pre = mock;
        while (node != null) {
            if (node.val == val) {
                pre.next = node.next;
            } else {
                pre = node;
            }
            node = node.next;
        }
        return mock.next;
    }


}

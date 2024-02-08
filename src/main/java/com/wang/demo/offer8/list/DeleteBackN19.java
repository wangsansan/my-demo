package com.wang.demo.offer8.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/7 13:49
 */
public class DeleteBackN19 extends BaseList {

    public static ListNode solution(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int i = 1;
        ListNode node = head;
        while (i < n) {
            node = node.next;
            if (node == null) {
                return head;
            }
            i++;
        }
        ListNode pre = null;
        ListNode node1 = head;
        while (node.next != null) {
            node = node.next;
            pre = node1;
            node1 = node1.next;
        }
        if (pre == null) {
            // 删除头结点
            return head.next;
        } else {
            pre.next = node1.next;
            node1.next = null;
            return head;
        }
    }

    /**
     * 只要头结点可能被删除，就可以mock出一个无意义的头结点出来
     */
    public ListNode solution1(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode mock = new ListNode(0);
        mock.next = head;
        ListNode fastIndex = mock;
        for (int i = 0; i <= n; i++) {
            fastIndex = fastIndex.next;
        }
        ListNode slowIndex = mock;
        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }
        slowIndex.next = slowIndex.next.next;
        return mock.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        solution(node1, 2);
    }

}

package com.wang.demo.offer8.list;

import javafx.util.Pair;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/7 13:17
 */
public class ReverseList206 extends BaseList{

    public ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Pair<ListNode, ListNode> pair = process(head);
        return pair.getValue();
    }

    private Pair<ListNode, ListNode> process(ListNode node) {
        if (node.next == null) {
            return new Pair<>(node, node);
        }
        Pair<ListNode, ListNode> pair = process(node.next);
        ListNode nextNode = pair.getKey();
        nextNode.next = node;
        node.next = null;
        return new Pair<>(node, pair.getValue());
    }

    public ListNode solution1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

}

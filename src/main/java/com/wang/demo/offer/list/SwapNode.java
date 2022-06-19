package com.wang.demo.offer.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/19 10:46 上午
 */

import java.util.List;
import java.util.Objects;

/**
 * 给定一个链表，两两交换相邻节点
 */
public class SwapNode extends AbstractList {

    public static ListNode solution(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.getNextNode())) {
            return head;
        }
        ListNode front, backend;
        backend = head;
        front = head.getNextNode();
        while (Objects.nonNull(front)) {
            int temp = front.getValue();
            front.setValue(backend.getValue());
            backend.setValue(temp);
            backend = front.getNextNode();
            if (Objects.isNull(backend)) {
                break;
            } else {
                front = backend.getNextNode();
            }
        }
        return head;
    }

    public static ListNode solution1(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.getNextNode())) {
            return head;
        }
        ListNode newHead = head.getNextNode();
        ListNode before = null;
        ListNode cur = head;
        ListNode next = newHead;
        while (Objects.nonNull(next)) {
            swapNeighNode(before, cur, next);
            before = cur;
            cur = before.getNextNode();
            if (Objects.isNull(cur)) {
                break;
            }
            next = cur.getNextNode();
        }


        return newHead;
    }

    private static void swapNeighNode(ListNode before, ListNode node1, ListNode node2) {
        ListNode nextNode = node2.getNextNode();
        if (Objects.nonNull(before)) {
            before.setNextNode(node2);
        }
        node2.setNextNode(node1);
        node1.setNextNode(nextNode);
    }

    public static void main(String[] args) {
        System.out.println(HEAD);
        System.out.println(solution1(HEAD));
    }

}

package com.wang.demo.offer.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/21 8:45 上午
 */

import java.util.List;
import java.util.Objects;

/**
 * Y形的交叉链表，找到交点
 */
public class FindTheNode {

    public static ListNode solution(ListNode head1, ListNode head2) {
        if (Objects.isNull(head1) || Objects.isNull(head2)) {
            return null;
        }

        ListNode index1 = head1;
        ListNode index2 = head2;

        while (Objects.nonNull(index1.getNextNode())
                && Objects.nonNull(index2.getNextNode())) {
            index1 = index1.getNextNode();
            index2 = index2.getNextNode();
        }

        int k = 0;
        if (Objects.isNull(index1.getNextNode())) {
            while (Objects.nonNull(index2.getNextNode())) {
                index2 = index2.getNextNode();
                k++;
            }
            index1 = head1;
            index2 = head2;
            while (k > 0) {
                index2 = index2.getNextNode();
                k--;
            }
        } else {
            while (Objects.nonNull(index1.getNextNode())) {
                index1 = index1.getNextNode();
                k++;
            }
            index1 = head1;
            index2 = head2;

            while (k > 0) {
                index1 = index1.getNextNode();
                k--;
            }
        }

        while (!index1.equals(index2)) {
            index1 = index1.getNextNode();
            index2 = index2.getNextNode();
        }

        return index1;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(3, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node5 = new ListNode(5, null);

        node1.setNextNode(node2);
        node2.setNextNode(node4);
        node3.setNextNode(node4);
        node4.setNextNode(node5);
        System.out.println(solution(node1, node3));
    }

}

package com.wang.demo.offer.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/20 11:37 下午
 */

import java.util.Objects;

/**
 * 返回链表的倒数第n个节点
 */
public class LastNNode extends AbstractList {

    public static ListNode solution(ListNode head, int n) {
        if (n < 1) {
            return head;
        }
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode front = head;
        /**
         * 因为倒数第一个是尾结点，此处判断条件写为1
         */
        while (n > 1) {
            front = front.getNextNode();
            if (Objects.isNull(front)) {
                return null;
            }
            n--;
        }
        ListNode backend = head;
        ListNode beforeBackEnd = head;
        while (Objects.nonNull(front.getNextNode())) {
            front = front.getNextNode();
            backend = backend.getNextNode();
            if (!beforeBackEnd.getNextNode().equals(backend)) {
                beforeBackEnd = beforeBackEnd.getNextNode();
            }
        }
        if (backend.equals(head)) {
            ListNode result = head.getNextNode();
            head.setNextNode(null);
            return result;
        }
        beforeBackEnd.setNextNode(backend.getNextNode());
        backend.setNextNode(null);
        return head;
    }

    public static void main(String[] args) {
        System.out.println(solution(HEAD, 1));
    }
}

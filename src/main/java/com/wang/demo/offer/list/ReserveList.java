package com.wang.demo.offer.list;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/18 1:04 下午
 */
public class ReserveList extends AbstractList{

    /**
     * 递归解法，时间复杂度O(n)，空间复杂度O(n)，和使用栈效果一样
     * @param head
     * @return
     */
    public static ListNode solution(ListNode head) {
        MyPair<ListNode, ListNode> nodePair = doSolution(head);
        // 这一步很关键，否则就变成双向链表了
        head.setNextNode(null);
        return nodePair.getRight();
    }

    public static MyPair<ListNode, ListNode> doSolution(ListNode node) {
        if (Objects.isNull(node.getNextNode())) {
            return MyPair.of(node, node);
        }
        MyPair<ListNode, ListNode> nodePair = doSolution(node.getNextNode());
        nodePair.getLef().setNextNode(node);
        return MyPair.of(node, nodePair.getRight());
    }

    /**
     * 双指针解法，时间复杂度O(n),空间复杂度O(1)
     * @param head
     * @return
     */
    public static ListNode solution2(ListNode head) {
        ListNode pre = head; ListNode cur = null;
        while (Objects.nonNull(pre)) {
            ListNode temp = pre.getNextNode();
            pre.setNextNode(cur);
            cur = pre;
            pre = temp;
        }

        return cur;
    }

    public static void main(String[] args) {
        System.out.println(HEAD);
        System.out.println(solution2(HEAD));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class MyPair<T, R> {
        T lef;
        R right;

        public static <M, N> MyPair<M, N> of(M left, N right) {
            return new MyPair<>(left, right);
        }
    }

}

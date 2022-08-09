package com.wang.demo.offer.list;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/22 7:40 上午
 */

/**
 * 返回链表的入环节点，如果没有，返回-1
 */
public class FindTheCircle {

    public static ListNode solution(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        /**
         * 此处的初始化还是挺重要的，important！！！
         */
        ListNode slow = head;
        ListNode fast = head;
        while (Objects.nonNull(fast)
                && Objects.nonNull(fast.getNextNode())) {
            slow = slow.getNextNode();
            fast = fast.getNextNode();
            if (Objects.nonNull(fast)) {
                fast = fast.getNextNode();
            }
            if (slow == fast) {
                /**
                 * 说明有环
                 */
                ListNode index1 = slow;
                ListNode index2 = head;
                /**
                 * 一个从头结点出发，一个从slow出发，最终相遇在入环点
                 */
                while (index1 != index2) {
                    index1 = index1.getNextNode();
                    index2 = index2.getNextNode();
                }
                return index1;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        List<ListNode> nodeList = Lists.newArrayList();
        int nodeNumber = 2;
        for (int i = 0; i < nodeNumber; i++) {
            nodeList.add(new ListNode(i, null));
        }

        for (int i = 0; i < nodeNumber - 1; i++) {
            nodeList.get(i).setNextNode(nodeList.get(i + 1));
        }
        ListNode head = nodeList.get(0);

//        nodeList.get(nodeNumber - 1).setNextNode(nodeList.get(0));
        ListNode solution = solution(head);
        System.out.println(Objects.nonNull(solution)? solution.getValue(): -1);
    }

}

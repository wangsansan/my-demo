package com.wang.demo.offer8.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/8 08:47
 */
public class ListCross160 extends BaseList {

    public static ListNode solution(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int aLength = processLength(headA);
        int bLength = processLength(headB);
        ListNode longHead = null;
        ListNode shortHead = null;
        if (aLength >= bLength) {
            longHead = headA;
            shortHead = headB;
        } else {
            longHead = headB;
            shortHead = headA;
        }
        int k = Math.abs(aLength - bLength);
        while (k > 0) {
            longHead = longHead.next;
            k--;
        }
        while (longHead != null && shortHead != null) {
            if (longHead == shortHead) {
                return longHead;
            }
            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        return null;
    }

    private static int processLength(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

}

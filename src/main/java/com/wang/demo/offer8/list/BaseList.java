package com.wang.demo.offer8.list;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/7 08:32
 */
public class BaseList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}

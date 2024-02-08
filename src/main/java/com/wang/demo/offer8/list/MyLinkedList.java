package com.wang.demo.offer8.list;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/2/7 11:18
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * leetcode707
 */
public class MyLinkedList extends BaseList{

    private ListNode head;

    private ListNode tail;

    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(int index) {
        ListNode node = getNode(index);
        if (node == null) {
            return -1;
        }
        return node.val;
    }

    private ListNode getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = 0;
        ListNode node = head;
        while (i < index) {
            node = node.next;
            i++;
        }
        return node;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            tail = node;
        } else {
            node.next = head;
        }
        head = node;
        size++;
    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            ListNode preNode = getNode(index - 1);
            ListNode next = preNode.next;
            ListNode node = new ListNode(val);
            preNode.next = node;
            node.next = next;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            ListNode node = head.next;
            head.next = null;
            head = node;
            if (node == null) {
                tail = null;
            }
        } else {
            ListNode preNode = getNode(index - 1);
            ListNode node = preNode.next;
            preNode.next = node.next;
            node.next = null;
            if (preNode.next == null) {
                tail = preNode;
            }
        }
        size--;
    }

    // ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
    // [[],[1],[3],[1,2],[1],[1],[1]]
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);
        myLinkedList.get(1);
        myLinkedList.deleteAtIndex(1);
        myLinkedList.get(1);
    }

}

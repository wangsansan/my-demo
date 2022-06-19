package com.wang.demo.offer.list;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/18 1:05 下午
 */
public class AbstractList {

    protected static ListNode HEAD = null;

    static {
        List<ListNode> nodeList = Lists.newArrayList();
        int nodeNumber = 9;
        for (int i = 0; i < nodeNumber; i++) {
            nodeList.add(new ListNode(i, null));
        }

        for (int i = 0; i < nodeNumber - 1; i++) {
            nodeList.get(i).setNextNode(nodeList.get(i + 1));
        }
        HEAD = nodeList.get(0);
    }

}

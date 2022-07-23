package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/23 9:19 上午
 */

import com.google.common.collect.Lists;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出搜索二叉树中的众数，出现频率最高的数
 */
public class MaxCountNumber extends AbstractPrintNode {

    static int count;

    static TreeNode pre;

    static int maxFreq;

    static List<Integer> result = new ArrayList<>();

    public static List<Integer> solution(TreeNode node) {
        doProcess(node);
        return result;
    }

    private static void doProcess(TreeNode node) {
        if (nodeIsNull(node)) {
            return;
        }

        doProcess(node.getLeft());

        Integer value = node.getValue();

        if (nodeIsNull(pre) || pre.getValue().equals(node.getValue())) {
            count++;
        } else {
            count = 1;
        }

        if (count > maxFreq) {
            result.clear();;
            result.add(value);
        } else if (count ==  maxFreq) {
            result.add(count);
        }

        doProcess(node.getRight());
    }
}

package com.wang.demo.offer.tree;

import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/15 8:40 上午
 */

/**
 * 二叉树的最小深度
 * 最小深度的定义：根节点到叶子节点的最小距离
 * 注意要到叶子节点才算是深度
 */
public class TreeMinHeight extends AbstractPrintNode {

    public static int solution(TreeNode node) {
        if (nodeIsNull(node)) {
            return Integer.MAX_VALUE;
        }
        if (isLeafNode(node)) {
            return 1;
        }
        return 1 + Math.min(solution(node.getLeft()), solution(node.getRight()));
    }

    public static void main(String[] args) {
        Integer[] array = {3, 9, 20, null, null, 15, 7};
        TreeNode node = convertToTree(array);
        System.out.println(solution(node));
        System.out.println(TreeMaxHeight.solution(node));
    }

}

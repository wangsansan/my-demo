package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/20 8:45 上午
 */

import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

/**
 * 合并两个二叉树
 */
public class MergeTree extends AbstractPrintNode {

    public static TreeNode solution(TreeNode node1, TreeNode node2) {
        if (nodeIsNull(node1) && nodeIsNull(node2)) {
            return null;
        }
        int newValue = newValue(node1, node2);
        TreeNode node = new TreeNode(newValue);
        node.setLeft(solution(getLeftChild(node1), getLeftChild(node2)));
        node.setRight(solution(getRightChild(node1), getRightChild(node2)));
        return node;
    }

    private static TreeNode getLeftChild(TreeNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        return node.getLeft();
    }

    private static TreeNode getRightChild(TreeNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        return node.getRight();
    }

    private static int newValue(TreeNode node1, TreeNode node2) {
        return defaultValue(node1) +  defaultValue(node2);
    }

    private static int defaultValue(TreeNode node) {
        return nodeIsNull(node) ? 0 : node.getValue();
    }

    public static void main(String[] args) {
        Integer[] array1 = {1, 3, 2, 5};
        Integer[] array2 = {2, 1, 3, null, 4, null, 7};
        TreeNode node1 = convertToTree(array1);
        TreeNode node2 = convertToTree(array2);
        TreeNode root = solution(node1, node2);
        LevelPrintNode.print(root);
    }

}

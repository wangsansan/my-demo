package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/21 7:33 上午
 */

import java.util.Objects;

/**
 * 从二叉搜索树中找到节点
 */
public class FindNode extends AbstractPrintNode {

    public static TreeNode solution(TreeNode node, int value) {
        if (nodeIsNull(node)) {
            return null;
        }

        if (node.getValue() == value) {
            return node;
        } else if (node.getValue() < value) {
            return solution(node.getRight(), value);
        } else {
            return solution(node.getLeft(), value);
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        System.out.println(solution(node1, 2));
    }

}

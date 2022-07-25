package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/25 8:24 上午
 */
public class DeleteFromBST extends AbstractPrintNode{

    public static void solution(TreeNode root, TreeNode value) {
        if (nodeIsNull(root)) {
            return;
        }
        if (root.getValue().equals(value.getValue())) {
            root = null;
            return;
        }
        process(null, root, value);
    }

    public static void process(TreeNode parent, TreeNode node, TreeNode value) {
        if (nodeIsNull(node)) {
            return;
        }
        if (node.getValue().equals(value.getValue())) {
            boolean leftChild = node.equals(parent.getLeft());
            // 1. 右子树为空的时候，用左子树代替当前节点
            if (nodeIsNull(node.getRight())) {
                if (leftChild) {
                    parent.setLeft(node.getLeft());
                } else {
                    parent.setRight(node.getLeft());
                }
            } else {
                // 2. 右子树不为空的时候，找到右子树上最小节点
                TreeNode smallestNode = findSmallestNode(node, node.getRight());
                smallestNode.setLeft(node.getLeft());
                smallestNode.setRight(node.getRight());
                if (leftChild) {
                    parent.setLeft(smallestNode);
                } else {
                    parent.setRight(smallestNode);
                }
            }
        } else if (node.getValue() < value.getValue()) {
            process(node, node.getRight(), value);
        } else {
            process(node, node.getLeft(), value);
        }

    }

    private static TreeNode findSmallestNode(TreeNode parent, TreeNode node) {
        if (nodeIsNull(node.getLeft())) {
            // 找到最小节点之后，让最小节点的父节点的左子树等于最小节点的右子树
            if (node.equals(parent.getLeft())) {
                // 3.1 右子树最小值不是右子树根节点的时候
                parent.setLeft(node.getRight());
            } else {
                // 3.2 右子树最小值是右子树根节点的时候
                parent.setRight(node.getRight());
            }
            return node;
        }

        return findSmallestNode(node, node.getLeft());
    }

    public static void main(String[] args) {
        Integer[] array = {5, 3, 6, 2, 4, null, 7};
        TreeNode root = convertToTree(array);
        int key = 3;
        TreeNode value = new TreeNode(key);
        LevelPrintNode.print(root);
        solution(root, value);
        LevelPrintNode.print(root);
    }

}

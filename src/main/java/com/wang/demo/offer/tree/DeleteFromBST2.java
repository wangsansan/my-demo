package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/25 8:24 上午
 */
public class DeleteFromBST2 extends AbstractPrintNode{

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
            TreeNode newNode = null;
            // 1. 右子树为空的时候，用左子树代替
            if (nodeIsNull(node.getRight())) {
                newNode = node.getLeft();
            } else {
                // 2. 右子树不为空，找到右子树最小节点
                newNode = node.getRight();
                TreeNode smallestNode = findSmallestNode(node, node.getRight());
                // 最小节点的左子树设置为原node节点的左子树
                smallestNode.setLeft(node.getLeft());
            }
            boolean leftChild = node.equals(parent.getLeft());
            if (leftChild) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
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

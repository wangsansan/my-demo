package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/24 10:47 上午
 */

/**
 * 给定一个二叉搜索树，插入一个节点
 */
public class InsertIntoBST extends AbstractPrintNode {

    public static void solution(TreeNode root, TreeNode value) {
        if (nodeIsNull(root)) {
            root = value;
            return;
        }
        process(null, root, value);
    }

    public static void process(TreeNode parent, TreeNode node, TreeNode value) {

        if (nodeIsNull(node)) {
            if (node == parent.getLeft()) {
                parent.setLeft(value);
            } else {
                parent.setRight(value);
            }
            return;
        }

        if (value.getValue() < node.getValue()) {
            process(node, node.getLeft(), value);
        } else {
            process(node, node.getRight(), value);
        }
    }

    public static void main(String[] args) {
        Integer[] array = {4,2,7,1,3};
        TreeNode root = convertToTree(array);
        LevelPrintNode.print(root);
        solution(root, new TreeNode(5));
        LevelPrintNode.print(root);
    }

}

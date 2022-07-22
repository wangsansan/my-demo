package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/22 7:59 上午
 */

/**
 * 求非负搜索二叉树的最小绝对差
 */
public class MinSub extends AbstractPrintNode{

    public static int solution(TreeNode node) {
        if (nodeIsNull(node)) {
            return Integer.MAX_VALUE;
        }

        int subValue = Math.min(subValue(node, node.getLeft()), subValue(node, node.getRight()));
        int childSubValue = Math.min(solution(node.getLeft()), solution(node.getRight()));
        return Math.min(subValue, childSubValue);
    }

    private static int subValue(TreeNode parent, TreeNode node) {
        if (nodeIsNull(node)) {
            return Integer.MAX_VALUE;
        }

        return Math.abs(parent.getValue() - node.getValue());
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.setRight(node3);
        node3.setLeft(node2);
        System.out.println(solution(node1));
    }

}

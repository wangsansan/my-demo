package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/28 8:11 上午
 */

/**
 * 根据搜索二叉树创建一棵累加树
 */
public class BuildSumBST extends AbstractPrintNode {

    static int current = 0;

    public static TreeNode solution(TreeNode root) {
        return process(root);
    }

    private static TreeNode process(TreeNode node) {
        if (nodeIsNull(node)) {
            return null;
        }

        process(node.getRight());
        current += node.getValue();
        node.setValue(current);
        process(node.getLeft());

        return node;
    }

    public static void main(String[] args) {
        Integer[] array = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        TreeNode node = convertToTree(array);
        LevelPrintNode.print(solution(node));
    }

}

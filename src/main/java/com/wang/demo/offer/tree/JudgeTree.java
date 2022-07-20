package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/21 7:40 上午
 */

/**
 * 判断二叉树是不是搜索二叉树
 */
public class JudgeTree extends AbstractPrintNode {

    /**
     * 递归，判断所有节点都是二叉树节点
     * @param node
     * @return
     */
    public static boolean solution(TreeNode node) {
        return isValidNode(node) && isValidNode(node.getLeft()) && isValidNode(node.getRight());
    }

    private static boolean isValidNode(TreeNode node) {
        if (nodeIsNull(node)) {
            return true;
        }

        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        boolean leftValid = nodeIsNull(left) || left.getValue() <= node.getValue();
        boolean rightValid = nodeIsNull(right) || right.getValue() >= node.getValue();
        return leftValid && rightValid;
    }

    public static void main(String[] args) {
        Integer[] array = {2, 1, 3};
        TreeNode treeNode = convertToTree(array);
        System.out.println(solution(treeNode));
        Integer[] array1 = {5, 1, 4, null, null, 3, 6};
        System.out.println(solution(convertToTree(array1)));
    }

}

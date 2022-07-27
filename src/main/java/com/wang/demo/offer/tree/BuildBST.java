package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/27 8:08 上午
 */

/**
 * 根据有序数组创建二叉搜索树
 */
public class BuildBST {

    public static TreeNode solution(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        return process(array, 0, array.length - 1);
    }

    public static TreeNode process(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return new TreeNode(array[start]);
        }

        int rootIndex = start / 2 + end / 2;
        TreeNode node = new TreeNode(array[rootIndex]);
        node.setLeft(process(array, start, rootIndex - 1));
        node.setRight(process(array, rootIndex + 1, end));
        return node;
    }

    public static void main(String[] args) {
        int[] array = {-10, -3, 0, 5, 9};
        TreeNode root = solution(array);
        LevelPrintNode.print(root);
    }

}

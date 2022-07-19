package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/19 8:20 上午
 */

import org.apache.commons.lang3.ArrayUtils;

/**
 * 根据给定的数组，返回最大的二叉树
 * 最大二叉树：根节点最大，左子树由最大值左边的数构成，右子树由最大值右边的数构成
 */
public class MaxTree {

    public static TreeNode solution(int[] array) {
        if (ArrayUtils.isEmpty(array)) {
            return null;
        }
        return doProcess(array, 0, array.length - 1);
    }

    public static TreeNode doProcess(int[] array, int start, int end) {
        int maxIndex = findMax(array, start, end);
        if (maxIndex == -1) {
            return null;
        }
        TreeNode treeNode = new TreeNode(array[maxIndex]);
        TreeNode leftChild = doProcess(array, start, maxIndex - 1);
        TreeNode rightChild = doProcess(array, maxIndex + 1, end);
        treeNode.setLeft(leftChild);
        treeNode.setRight(rightChild);
        return treeNode;
    }

    private static int findMax(int[] array, int start, int end) {
        if (ArrayUtils.isEmpty(array) || end < start) {
            return -1;
        }

        if (end ==  start) {
            return start;
        }

        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public static void main(String[] args) {
        int array[] = {3, 2, 1, 6, 0, 5};
        TreeNode root = solution(array);
        LevelPrintNode.print(root);
    }

}

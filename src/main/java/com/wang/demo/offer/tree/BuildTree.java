package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/18 9:21 上午
 */

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * 根据中序和后序，构建二叉树
 */
public class BuildTree {

    public static TreeNode solution(int[] middle, int[] back) {
        if (ArrayUtils.isEmpty(middle) || ArrayUtils.isEmpty(back)) {
            return null;
        }

        int lastElement = back[back.length - 1];
        int index = findIndex(lastElement, middle);
        if (index == -1) {
            return null;
        }

        int[] middleBefore = new int[index];
        int[] middleAfter = new int[middle.length - 1 - index];
        int[] newBackLeft = new int[middleBefore.length];
        int[] newBackRight = new int[middleAfter.length];
        System.arraycopy(middle, 0, middleBefore, 0, middleBefore.length);
        System.arraycopy(middle, index + 1, middleAfter, 0, middleAfter.length);
        System.arraycopy(back, 0, newBackLeft, 0, newBackLeft.length);
        System.arraycopy(back, newBackLeft.length, newBackRight, 0, newBackRight.length);

        TreeNode leftChild = solution(middleBefore, newBackLeft);
        TreeNode rightChild = solution(middleAfter, newBackRight);
        TreeNode node = new TreeNode(lastElement);
        node.setLeft(leftChild);
        node.setRight(rightChild);
        return node;
    }

    private static int findIndex(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] middle = {9, 3, 15, 20, 7};
        int[] back = {9, 15, 7, 20, 3};
        TreeNode root = solution(middle, back);
        LevelPrintNode.print(root);
    }

}

package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/12 7:07 上午
 */
public class AbstractPrintNode {

    public static TreeNode root;

    static {
        root = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode11 = new TreeNode(11);
        root.setLeft(treeNode2);
        root.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);
        treeNode4.setLeft(treeNode8);
        treeNode4.setRight(treeNode9);
        treeNode5.setLeft(treeNode10);
        treeNode5.setRight(treeNode11);
    }

    public static TreeNode convertToTree(Integer[] array) {
        TreeNode[] tree = new TreeNode[array.length];
        for (int i = 0; i < array.length; i++) {
            tree[i] = new TreeNode(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            if (2 * i + 1 < array.length) {
                tree[i].setLeft(tree[2 * i + 1]);
            }
            if (2 * i + 2 < array.length) {
                tree[i].setRight(tree[2 * i + 2]);
            }
        }
        return tree[0];
    }

}

package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/26 7:48 上午
 */

/**
 * 修剪二叉搜索树
 * 根据给定的范围删除节点
 */
public class dealBST extends AbstractPrintNode {

    public static TreeNode solution(TreeNode root, int min, int max) {
        if (nodeIsNull(root)) {
            return null;
        }

        if (root.getValue() < min) {
            return solution(root.getRight(), min, max);
        }

        if (root.getValue() > max) {
            return solution(root.getLeft(), min, max);
        }

        root.setLeft(solution(root.getLeft(), min, max));
        root.setRight(solution(root.getRight(), min, max));
        return root;
    }



}

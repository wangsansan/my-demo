package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/27 8:57 上午
 */

import com.wang.demo.offer.tree.TreeNode;

import java.util.Objects;

/**
 * 比较两个二叉树是否相等
 */
public class SameValueTree {

    public static boolean solution(TreeNode root1, TreeNode root2) {
        return compare(root1, root2);
    }

    private static boolean compare(TreeNode node1, TreeNode node2) {
        if (Objects.isNull(node1) && Objects.nonNull(node2)) {
            return false;
        }

        if (Objects.isNull(node2) && Objects.nonNull(node1)) {
            return false;
        }

        if (node1.getValue() != node2.getValue()) {
            return false;
        }

        boolean leftSame = compare(node1.getLeft(), node2.getLeft());
        if (!leftSame) {
            return false;
        }
        return compare(node1.getRight(), node2.getRight());
    }

}

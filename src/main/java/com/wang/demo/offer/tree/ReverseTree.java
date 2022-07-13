package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/13 8:20 上午
 */

import java.util.Objects;

/**
 * 翻转二叉树
 */
public class ReverseTree extends AbstractPrintNode {

    public static void solution(TreeNode node) {

        if (Objects.isNull(node)) {
            return;
        }

        if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
            return;
        }

        solution(node.getLeft());

        solution(node.getRight());

        TreeNode temp = node.getRight();

        node.setRight(node.getLeft());

        node.setLeft(temp);
    }

    public static void main(String[] args) {
        LevelPrintNode.print(root);
        solution(root);
        LevelPrintNode.print(root);
    }

}

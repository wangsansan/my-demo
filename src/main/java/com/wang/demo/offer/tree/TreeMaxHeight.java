package com.wang.demo.offer.tree;

import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/15 8:40 上午
 */

/**
 * 求二叉树的最大深度
 */
public class TreeMaxHeight extends AbstractPrintNode {

    public static int solution(TreeNode node) {
        if (nodeIsNull(node)) {
            return 0;
        }
        return 1 + Math.max(solution(node.getLeft()), solution(node.getRight()));
    }

    public static void main(String[] args) {
        System.out.println(solution(root));
    }

}

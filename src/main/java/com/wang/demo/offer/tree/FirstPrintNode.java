package com.wang.demo.offer.tree;

import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/13 7:00 上午
 */
public class FirstPrintNode extends AbstractPrintNode {

    public static void print(TreeNode node) {
        System.out.println(node.getValue());
        if (Objects.nonNull(node.getLeft())) {
            print(node.getLeft());
        }
        if (Objects.nonNull(node.getRight())) {
            print(node.getRight());
        }
    }

    public static void main(String[] args) {
        print(root);
    }
}

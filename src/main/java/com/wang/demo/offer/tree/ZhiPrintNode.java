package com.wang.demo.offer.tree;

import java.util.Objects;
import java.util.Stack;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/12 7:10 上午
 */
public class ZhiPrintNode extends AbstractPrintNode {

    public static void print(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                if (Objects.nonNull(node.getLeft())) {
                    stack2.push(node.getLeft());
                }
                if (Objects.nonNull(node.getRight())) {
                    stack2.push(node.getRight());
                }
                System.out.print(node.getValue() + " ");
                if (stack1.isEmpty()) {
                    System.out.println();
                }
            }
            while (!stack2.isEmpty()) {
                TreeNode node = stack2.pop();
                if (Objects.nonNull(node.getRight())) {
                    stack1.push(node.getRight());
                }
                if (Objects.nonNull(node.getLeft())) {
                    stack1.push(node.getLeft());
                }
                System.out.print(node.getValue() + " ");
                if (stack2.isEmpty()) {
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        print(root);
    }

}

package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/28 9:00 上午
 */

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 填充每个节点的下一个右侧节点指针
 */
public class SetTreeNodeNext {

    @Data
    static class NewTreeNode {
        private int value;
        private NewTreeNode left;
        private NewTreeNode right;
        private NewTreeNode next;

        public NewTreeNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            String rightString = Objects.isNull(next) ? "#" : String.valueOf(next.getValue());
            return value + "->" + rightString;
        }
    }

    public static void solution(NewTreeNode root) {
        process(null, root);
    }

    private static void process(NewTreeNode parent, NewTreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }
        /**
         * 此处我的解法是把当前节点当成子节点来做的
         * 也可以不传父节点，把当前节点当成子节点，直接通过当前节点和next节点来设置子节点之间的关系
         * 不管是用上面两种中的哪一种，然后都先序遍历
         */
        // 设置当前节点的next
        if (Objects.isNull(parent)) {
            node.setNext(null);
        } else {
            if (Objects.nonNull(parent.getLeft()) && node == parent.getLeft()) {
                // 当前节点是父节点的左子树
                node.setNext(parent.getRight());
            } else if (Objects.nonNull(parent.getRight()) && node == parent.getRight()) {
                // 当前节点是父节点的右子树
                NewTreeNode parentNext = parent.getNext();
                if (Objects.nonNull(parentNext)) {
                    node.setNext(parentNext.getLeft());
                }
            }
        }

        process(node, node.getLeft());

        process(node, node.getRight());
    }

    public static void main(String[] args) {
        int n = 7;
        List<NewTreeNode> nodeList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nodeList.add(new NewTreeNode(i + 1));
        }
        for (int i = 0; i < n; i++) {
            int leftIndex = 2 * i + 1;
            if (leftIndex < n) {
                nodeList.get(i).setLeft(nodeList.get(leftIndex));
            }
            int rightIndex = 2 *  i + 2;
            if (rightIndex < n) {
                nodeList.get(i).setRight(nodeList.get(rightIndex));
            }
        }

        NewTreeNode root = nodeList.get(0);
        solution(root);
        System.out.println("=========");

    }

}

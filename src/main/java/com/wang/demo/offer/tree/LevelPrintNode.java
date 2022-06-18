package com.wang.demo.offer.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/11 9:42 下午
 */
public class LevelPrintNode extends AbstractPrintNode {


    public static void print(TreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        int currentLevel = 1;
        int nextLevel = 0;
        while (queue.size() > 0 ) {
            TreeNode currentNode = queue.poll();
            if (Objects.nonNull(currentNode.getLeft())) {
                nextLevel++;
                queue.add(currentNode.getLeft());
            }
            if (Objects.nonNull(currentNode.getRight())) {
                nextLevel++;
                queue.add(currentNode.getRight());
            }
            System.out.print(currentNode.getValue() + " ");
            currentLevel--;
            if (currentLevel == 0 ) {
                System.out.println();
                currentLevel = nextLevel;
                nextLevel = 0;
            }
        }
    }

    public static void main(String[] args) {
        print(root);
    }

}

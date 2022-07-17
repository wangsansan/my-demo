package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/17 7:13 下午
 */

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 找到二叉树最后一层的最左边的节点
 */
public class LeftLastLayerNode extends AbstractPrintNode {

    public static TreeNode solution(TreeNode node) {
        if (Objects.isNull(node)) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode currentResult = null;
        queue.offer(node);
        int currentLevel = 1;
        int nextLevel = 0;
        boolean currentLayerSet = false;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (nextLevel == 0 && !currentLayerSet) {
                currentResult = poll;
                currentLayerSet = true;
            }
            if (Objects.nonNull(poll.getLeft())) {
                queue.offer(poll.getLeft());
                nextLevel++;
            }
            if (Objects.nonNull(poll.getRight())) {
                queue.offer(poll.getRight());
                nextLevel++;
            }
            currentLevel--;
            if (currentLevel == 0) {
                currentLevel = nextLevel;
                nextLevel = 0;
                currentLayerSet = false;
            }
        }

        return currentResult;
    }

    public static void main(String[] args) {
        TreeNode node = solution(root);
        System.out.println(node.getValue());
    }

}

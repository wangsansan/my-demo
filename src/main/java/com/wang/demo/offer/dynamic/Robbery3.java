package com.wang.demo.offer.dynamic;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/9/16 9:01 上午
 */

import com.wang.demo.offer.tree.TreeNode;
import javafx.util.Pair;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 打家劫舍二叉树版
 * 也是不能偷相邻的节点
 */
public class Robbery3 {

    /**
     * 采用后续遍历
     */

    public static int solution(TreeNode root) {
        Map<TreeNode, Pair<Integer, Integer>> money = new HashMap<>();
        doProcess(root, money);
        Pair<Integer, Integer> pair = money.get(root);
        return Math.max(pair.getKey(), pair.getValue());
    }

    private static void doProcess(TreeNode node, Map<TreeNode, Pair<Integer, Integer>> money) {
        if (Objects.isNull(node) || Objects.isNull(node.getValue())) {
            money.put(node, new Pair<>(0,0));
            return;
        }

        doProcess(node.getLeft(), money);
        doProcess(node.getRight(), money);
        Pair<Integer, Integer> leftPair = money.get(node.getLeft());
        Pair<Integer, Integer> rightPair = money.get(node.getRight());
        // 如果当前节点不偷(当前节点不偷，不代表一定要偷孩子节点)
        int notRob = Math.max(leftPair.getKey(), leftPair.getValue()) + Math.max(rightPair.getKey(), rightPair.getValue());
        // 如果当前节点偷，那么应该左右孩子都不偷（当前节点偷，代表一定不能偷孩子节点）
        int rob = leftPair.getKey() + rightPair.getKey() + node.getValue();
        money.put(node, new Pair<>(notRob, rob));
    }

    private static TreeNode buildTree(Integer[] values) {
        TreeNode[] nodes = new TreeNode[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new TreeNode(values[i]);
        }
        for (int i = 0; i < values.length; i++) {
            if (i * 2 + 1 < values.length) {
                nodes[i].setLeft(nodes[i * 2 + 1]);
            }
            if (i * 2 + 2 < values.length) {
                nodes[i].setRight(nodes[i * 2 + 2]);
            }
        }
        return nodes[0];
    }

    public static void main(String[] args) {
        Integer[] values = {3, 2, 3, null, 3, null, 1};
        TreeNode root = buildTree(values);
        System.out.println(solution(root));
        Integer[] values1 = {3, 4, 5, 1, 3, null, 1};
        TreeNode root1 = buildTree(values1);
        System.out.println(solution(root1));

    }

}

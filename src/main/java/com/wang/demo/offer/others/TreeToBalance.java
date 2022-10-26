package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/26 8:41 上午
 */

import com.wang.demo.offer.tree.LevelPrintNode;
import com.wang.demo.offer.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * 如果有多种构造方法，请你返回任意一种。
 */

public class TreeToBalance {

    public static TreeNode solution(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        process(root, values);
        return buildBalanceTree(values);
    }

    private static TreeNode buildBalanceTree(List<Integer> values) {
        return doBuildBalanceTree(values, 0, values.size() - 1);
    }

    private static TreeNode doBuildBalanceTree(List<Integer> values, int start, int end) {
        if (start > end) {
            return null;
        }
        /**
         * 此处需要注意，
         * 为了防止 start+end 的值超出integer的范围而溢出
         * mid的取值很值得注意
         */
        int mid = start + (end - start) / 2;
        Integer value = values.get(mid);
        TreeNode left = doBuildBalanceTree(values, start, mid - 1);
        TreeNode right = doBuildBalanceTree(values, mid + 1, end);
        return new TreeNode(value, left, right);
    }

    private static void process(TreeNode node, List<Integer> values) {
        if (Objects.isNull(node) || Objects.isNull(node.getValue())) {
            return;
        }

        process(node.getLeft(), values);

        values.add(node.getValue());

        process(node.getRight(), values);
    }

    public static void main(String[] args) {
        List<TreeNode> nodeList = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            nodeList.add(new TreeNode(i + 1));
        }
        for (int i = 0; i < nodeList.size(); i++) {
            if (i + 1 > nodeList.size() - 1) {
                break;
            }
            nodeList.get(i).setRight(nodeList.get(i + 1));
        }
        TreeNode root = nodeList.get(0);
        LevelPrintNode.print(root);
        TreeNode newRoot = solution(root);
        LevelPrintNode.print(newRoot);
    }

}

package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/17 8:10 上午
 */

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;

import java.util.List;
import java.util.Objects;

/**
 * 求出所有左边叶子节点的和
 */
public class LeftLeafNodeSum extends AbstractPrintNode{

    public static int solution(TreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }

        List<Integer> result = Lists.newLinkedList();
        if (Objects.nonNull(node.getLeft())) {
            doProcess(node.getLeft(), node, result);
        }

        if (Objects.nonNull(node.getRight())) {
            doProcess(node.getRight(), node, result);
        }

        return ListUtils.emptyIfNull(result).stream()
                .reduce(0, Integer::sum);
    }

    private static void doProcess(TreeNode node, TreeNode parent, List<Integer> result) {
        if (isLeftLeafNode(node, parent)) {
            result.add(node.getValue());
        }

        if (Objects.nonNull(node.getLeft())) {
            doProcess(node.getLeft(), node, result);
        }

        if (Objects.nonNull(node.getRight())) {
            doProcess(node.getRight(), node, result);
        }
    }

    private static boolean isLeftLeafNode(TreeNode node, TreeNode parent) {
        return Objects.nonNull(node) &&
                isLeafNode(node) &&
                Objects.nonNull(parent.getLeft()) &&
                parent.getLeft() == node;
    }

    public static void main(String[] args) {
        System.out.println(solution(root));
    }

}

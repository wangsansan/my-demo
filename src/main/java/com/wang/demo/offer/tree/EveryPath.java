package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/17 7:53 上午
 */

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;
import java.util.StringJoiner;

/**
 * 打印出二叉树所有的路径：根节点到叶子节点
 */
public class EveryPath extends AbstractPrintNode {

    public static List<String> solution(TreeNode node) {
        List<String> result = Lists.newLinkedList();
        Stack<TreeNode> path = new Stack<>();
        doProcess(node, result, path);
        return result;
    }

    private static void doProcess(TreeNode node, List<String> result, Stack<TreeNode> path) {
        path.push(node);
        if (isLeafNode(node)) {
            StringJoiner stringJoiner = new StringJoiner("->");
            for (int i = 0; i < path.size(); i++) {
                stringJoiner.add(String.valueOf(path.get(i).getValue()));
            }
            result.add(stringJoiner.toString());
        }

        if (!nodeIsNull(node.getLeft())) {
            doProcess(node.getLeft(), result, path);
            path.pop();
        }

        if (!nodeIsNull(node.getRight())) {
            doProcess(node.getRight(), result, path);
            path.pop();
        }
    }

    public static void main(String[] args) {
        List<String> result = solution(root);
        for (String s : result) {
            System.out.println(s);
        }
    }

}

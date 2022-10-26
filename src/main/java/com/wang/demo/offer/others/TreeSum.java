package com.wang.demo.offer.others;

import com.wang.demo.offer.tree.TreeNode;

import java.util.*;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/25 8:56 上午
 */

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TreeSum {

    public static int solution(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> numbers = new ArrayList<>();
        process(root, stack, numbers);
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static void process(TreeNode node, Stack<TreeNode> stack, List<Integer> numbers) {
        if (Objects.isNull(node)) {
            return;
        }
        stack.push(node);
        if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < stack.size(); i++) {
                stringBuilder.append(stack.get(i).getValue());
            }
            numbers.add(Integer.valueOf(stringBuilder.toString()));
            stack.pop();
            return;
        }

        process(node.getLeft(), stack, numbers);

        process(node.getRight(), stack, numbers);

        stack.pop();
    }

    public static void main(String[] args) {
        TreeNode treeNode = buildTree("4,9,0,5,1");
        System.out.println(solution(treeNode));
    }


    private static TreeNode buildTree(String value) {
        String[] array = value.split(",");
        List<TreeNode> nodeList = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new TreeNode(Integer.valueOf(array[i])));
        }
        for (int i = 0; i < nodeList.size(); i++) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            if (leftIndex < nodeList.size()) {
                TreeNode left = nodeList.get(leftIndex);
                nodeList.get(i).setLeft(left);
            }
            if (rightIndex < nodeList.size()) {
                TreeNode right = nodeList.get(rightIndex);
                nodeList.get(i).setRight(right);
            }
        }
        return nodeList.get(0);
    }

}

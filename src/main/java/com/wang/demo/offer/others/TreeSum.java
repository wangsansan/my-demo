package com.wang.demo.offer.others;

import com.wang.demo.offer.tree.TreeNode;

import java.util.*;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/25 8:56 上午
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

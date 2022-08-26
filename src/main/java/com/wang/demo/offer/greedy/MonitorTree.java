package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/26 8:42 上午
 */

import java.util.Objects;

/**
 * 监控二叉树，至少需要几个摄像头
 * 假设某个节点上放置一个摄像头，可以监控其父节点和子节点
 */
public class MonitorTree {

    private static int count = 0;

    /**
     * 0:未覆盖
     * 1：放摄像头
     * 2：被覆盖
     */
    public static int solution(TreeNode root) {
        return doProcess(root, root);
    }

    public static int doProcess(TreeNode root, TreeNode node) {
        // 本身自己就是空节点的话，不能当成叶子节点处理
        if (TreeNode.isNullNode(node)) {
            return -1;
        }
        // 由于有该处判断，因为遍历结果不可能得到-1， -1，所以下面的正式逻辑并未对-1，-1这种情况进行处理
        if (TreeNode.isLeaf(node)) {
            return 0;
        }

        int left = solution(node.left);
        int right = solution(node.right);
        /**
         * 子节点中任意有一个没被覆盖，需要放置一个摄像头
         */
        if (left == 0 || right == 0) {
            count++;
            return 1;
        }
        /**
         * 1, 1
         * 1, 2
         * 2, 1
         * 2, 2
         */
        if (left == 1 || right == 1) {
            return 2;
        }

        /**
         * 父节点如果仅有的
         */
        if (left == 2 && right == 2) {
            return 0;
        }

        // 可能由于上面的left = 2 && right = 2，最后剩下的节点是root，此时需要额外判断一下
        if (root.value == 0) {
            count++;
            return 1;
        }

        return -1;
    }

    static class TreeNode {
        Integer value;

        TreeNode left;

        TreeNode right;

        TreeNode(Integer value) {
            this.value = value;
        }

        TreeNode() {
        }

        static boolean isLeaf(TreeNode node) {
            return  (isNullNode(node.left) && isNullNode(node.right));
        }

        static boolean isNullNode(TreeNode node) {
            return Objects.isNull(node) || Objects.isNull(node.value);
        }

        static boolean ifFull(TreeNode node) {
            return Objects.nonNull(node.left) && Objects.nonNull(node.right);
        }
    }

    private static TreeNode convert(Integer[] values) {
        TreeNode root = new TreeNode();
        if (values.length == 1) {
            if (Objects.isNull(values[0])) {
                return null;
            } else {
                root.value = values[0];
                return root;
            }
        }

        root.value = values[0];
        TreeNode parent = root;
        for (int i = 1; i < values.length; i++) {
            TreeNode current = new TreeNode(values[i]);
            if (Objects.isNull(parent.left)) {
                parent.left = current;
            } else if (Objects.isNull(parent.right)) {
                parent.right = current;
            }
            if (TreeNode.ifFull(parent)) {
                if (!TreeNode.isNullNode(parent.left)) {
                    parent = parent.left;
                } else if (!TreeNode.isNullNode(parent.right)) {
                    parent = parent.right;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] values = {0, 0, null, 0, 0};
        TreeNode root = convert(values);
        solution(root);
        System.out.println(count);
        count = 0;
        Integer[] values1 = {0, 0, null, 0, null, 0, null, null, 0};
        TreeNode root1 = convert(values1);
        solution(root1);
        System.out.println(count);
    }

}

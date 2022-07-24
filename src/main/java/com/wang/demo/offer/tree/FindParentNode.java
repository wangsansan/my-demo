package com.wang.demo.offer.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/11 9:40 下午
 */
public class FindParentNode extends AbstractPrintNode{

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TreeNodeInfo {

        private TreeNode treeNode;

        private boolean findNode1;

        private boolean findNode2;

    }

    public static TreeNodeInfo findNearestParent(TreeNode treeNode, TreeNode treeNode1, TreeNode treeNode2) {
        if (nodeIsNull(treeNode)) {
            return new TreeNodeInfo(null, false, false);
        }

        boolean findNode1 = treeNode.getValue().equals(treeNode1.getValue());
        boolean findNode2 = treeNode.getValue().equals(treeNode2.getValue());
        TreeNode targetNode = null;
        if (Objects.nonNull(treeNode.getLeft())) {
            TreeNodeInfo leftInfo = findNearestParent(treeNode.getLeft(), treeNode1, treeNode2);
            findNode1 = findNode1 || leftInfo.findNode1;
            findNode2 = findNode1 || leftInfo.findNode2;
            if (Objects.nonNull(leftInfo.getTreeNode())) {
                targetNode = leftInfo.getTreeNode();
            }
        }

        if (Objects.nonNull(treeNode.getRight()) && Objects.isNull(targetNode)) {
            TreeNodeInfo rightInfo = findNearestParent(treeNode.getRight(), treeNode1, treeNode2);
            findNode1 = findNode1 || rightInfo.findNode1;
            findNode2 = findNode1 || rightInfo.findNode2;
            if (Objects.nonNull(rightInfo.getTreeNode())) {
                targetNode = rightInfo.getTreeNode();
            }
        }

        if (Objects.isNull(targetNode)) {
            if (findNode1 && findNode2) {
                targetNode = treeNode;
            }
        }

        return new TreeNodeInfo(targetNode, findNode1, findNode2);
    }

    public static void main(String[] args) {
        TreeNode first = new TreeNode(5);
        TreeNode second = new TreeNode(11);
        TreeNodeInfo parent = findNearestParent(root, first, second);
        System.out.println(parent);
    }

}

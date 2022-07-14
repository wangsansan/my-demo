package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/13 8:31 上午
 */

import com.google.common.collect.Lists;
import com.wang.demo.utils.CollectionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 判断二叉树是不是对称二叉树
 */
public class MirrorTree extends AbstractPrintNode {

    /**
     * 中序遍历，然后判断遍历结果是否是对称的
     * @param node
     * @return
     */
    public static boolean solution(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        middlePrint(node, result);
        return middleList(result);
    }

    /**
     * 左子树翻转，然后判断是否和右子树想等
     * @param node
     * @return
     */
    public static boolean solution2(TreeNode node) {
        ReverseTree.solution(node.getLeft());
        return treeEquals(node.getLeft(), node.getRight());
    }

    /**
     * 左子树 左中右 遍历，右子树 右中左 遍历，判断两个遍历结果是否相等
     * @param node
     * @return
     */
    public static boolean solution3(TreeNode node) {
        List<Integer> leftResult = Lists.newLinkedList();
        List<Integer> rightResult = Lists.newLinkedList();
        leftMiddle(node.getLeft(), leftResult);
        rightMiddle(node.getRight(), rightResult);
        // 注意，此处的isEqualCollection判断的是无序的，所以认为二者想等
        return StringUtils.equals(CollectionUtil.mapString(leftResult), CollectionUtil.mapString(rightResult));
    }

    public static void leftMiddle(TreeNode node, List<Integer> result) {
        if (Objects.nonNull(node.getLeft())) {
            leftMiddle(node.getLeft(), result);
        } else {
            result.add(null);
        }

        result.add(node.getValue());

        if (Objects.nonNull(node.getRight())) {
            leftMiddle(node.getRight(), result);
        } else {
            result.add(null);
        }
    }

    public static void rightMiddle(TreeNode node, List<Integer> result) {
        if (Objects.nonNull(node.getRight())) {
            rightMiddle(node.getRight(), result);
        } else {
            result.add(null);
        }

        result.add(node.getValue());

        if (Objects.nonNull(node.getLeft())) {
            rightMiddle(node.getLeft(), result);
        } else {
            result.add(null);
        }
    }

    public static boolean treeEquals(TreeNode node1, TreeNode node2) {
        if (Objects.isNull(node1) && Objects.isNull(node2)) {
            return true;
        }
        if (Objects.isNull(node1)) {
            return false;
        }

        if (Objects.isNull(node2)) {
            return false;
        }

        boolean equal = Objects.equals(node1.getValue(), node2.getValue());
        return equal && treeEquals(node1.getLeft(), node2.getLeft()) && treeEquals(node1.getRight(), node2.getRight());
    }

    private static boolean middleList(List<Integer> list) {
        if (CollectionUtils.size(list) < 2) {
            return true;
        }
        List<String> result = list.stream().map(String::valueOf).collect(Collectors.toList());
        System.out.println(result);
        int index1 = 0; int index2 = list.size() - 1;
        while (index1 <= index2) {
            if (!result.get(index1).equals(result.get(index2))) {
                return false;
            }
            index1++;
            index2--;
        }
        return true;
    }

    private static void middlePrint(TreeNode node, List<Integer> result) {
        if (Objects.isNull(node)) {
            return;
        }
        if (Objects.nonNull(node.getLeft())) {
            middlePrint(node.getLeft(), result);
        } else {
            if (Objects.nonNull(node.getRight())) {
                result.add(null);
            }
        }

        result.add(node.getValue());

        if (Objects.nonNull(node.getRight())) {
            middlePrint(node.getRight(), result);
        } else {
            if (Objects.nonNull(node.getLeft())) {
                result.add(null);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 2, 3, 4, 4, 3};
        TreeNode treeNode = convertToTree(array);
        System.out.println(solution(treeNode));
        System.out.println(solution3(treeNode));
        System.out.println(solution2(treeNode));
        array = new Integer[]{1, 2, 2, null, 3, null, 3};
        treeNode = convertToTree(array);
        System.out.println(solution(treeNode));
        System.out.println(solution3(treeNode));
        System.out.println(solution2(treeNode));
    }
}

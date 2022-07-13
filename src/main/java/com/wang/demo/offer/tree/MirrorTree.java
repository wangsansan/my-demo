package com.wang.demo.offer.tree;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/13 8:31 上午
 */

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 判断二叉树是不是对称二叉树
 */
public class MirrorTree extends AbstractPrintNode {

    public static boolean solution(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        middlePrint(node, result);
        return middleList(result);
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
        System.out.println(solution(convertToTree(array)));
        array = new Integer[]{1, 2, 2, null, 3, null, 3};
        System.out.println(solution(convertToTree(array)));
    }
}

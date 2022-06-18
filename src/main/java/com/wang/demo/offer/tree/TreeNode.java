package com.wang.demo.offer.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/11 9:39 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private int value;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

}

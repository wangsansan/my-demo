package com.wang.demo.offer.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/18 1:05 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListNode {

    private int value;

    private ListNode nextNode;

}

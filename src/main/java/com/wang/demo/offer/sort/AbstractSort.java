package com.wang.demo.offer.sort;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/10/24 7:25 上午
 */
public abstract class AbstractSort implements DataSort {

    @Override
    public void sort() {
        List<Integer> data = dataList;
        if (CollectionUtils.size(data) <= 1) {
            return;
        }
        doSort(data);
    }

    public abstract void doSort(List<Integer> data);
}

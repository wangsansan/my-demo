package com.wang.demo.offer.sort;

import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/10/24 7:23 上午
 */
public class QuickSort extends AbstractSort {


    @Override
    public void doSort(List<Integer> data) {
        int startIndex = 0;
        int endIndex = data.size() - 1;
        doQuickSort(data, startIndex, endIndex);
    }

    private void doQuickSort(List<Integer> data, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int i = startIndex;
        int j = endIndex;
        int index = i;
        int indexValue = data.get(index);
        while (i < j) {
            while (data.get(j) >= indexValue && i < j) {
                j--;
            }

            data.set(i, data.get(j));

            while (data.get(i) <= indexValue && i < j) {
                i++;
            }
            data.set(j, data.get(i));
        }
        if (i == j) {
            data.set(i, indexValue);
            doQuickSort(data, startIndex, i - 1);
            doQuickSort(data, i + 1, endIndex);
        }
    }

    public static void main(String[] args) {
        DataSort dataSort = new QuickSort();
        System.out.println(dataList);
        dataSort.sort();
        System.out.println(dataList);
    }
}

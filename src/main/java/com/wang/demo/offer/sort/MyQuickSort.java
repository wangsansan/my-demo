package com.wang.demo.offer.sort;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/10 8:15 上午
 */
public class MyQuickSort extends AbstractSort {


    @Override
    public void doSort(List<Integer> data) {
        doQuickSort(data, 0, data.size() - 1);
    }

    private void doQuickSort(List<Integer> data, int index1, int index2) {
        if (index1 >= index2) {
            return;
        }
        Integer indexData = data.get(index1);
        int front = index1;
        int backend = index2;
        while (front < backend) {
            while (data.get(backend) >= indexData && front < backend) {
                backend--;
            }
            if (front < backend) {
                Collections.swap(data, front, backend);
            }
            while (data.get(front) <= indexData && front < backend) {
                front++;
            }
            if (front < backend) {
                Collections.swap(data, front, backend);
            }
        }
        if (backend == front) {
            doQuickSort(data, index1, front - 1);
            doQuickSort(data, backend + 1, index2);
        }
    }

    public static void main(String[] args) {
        MyQuickSort test = new MyQuickSort();
        System.out.println(MyQuickSort.dataList);
        test.sort();
        System.out.println(MyQuickSort.dataList);
    }
}

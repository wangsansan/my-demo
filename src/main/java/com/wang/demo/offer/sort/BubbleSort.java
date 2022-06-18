package com.wang.demo.offer.sort;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/10/24 7:12 上午
 */
public class BubbleSort extends AbstractSort{

    @Override
    public void doSort(List<Integer> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            boolean swap = false;
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(i) > data.get(j)) {
                    swap = true;
                    Collections.swap(data, i, j);
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(dataList);
        BubbleSort test = new BubbleSort();
        test.sort();
        System.out.println(dataList);
    }
}

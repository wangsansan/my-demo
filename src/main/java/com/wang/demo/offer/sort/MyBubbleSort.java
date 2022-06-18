package com.wang.demo.offer.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/9 9:23 上午
 */
public class MyBubbleSort extends AbstractSort {

    @Override
    public void doSort(List<Integer> data) {

        for (int i = 0; i < data.size(); i++) {
            boolean moveFlag = false;
            for (int j = i; j < data.size() - 1; j++) {
                if (data.get(j) > data.get(j + 1)) {
                    int temp = data.get(j + 1);
                    data.set(j + 1, data.get(j));
                    data.set(j, temp);
                    moveFlag = true;
                }
            }
            if (!moveFlag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<Integer>() {
            {
                add(5);
                add(4);
                add(3);
                add(2);
                add(1);
            }
        };
        System.out.println(dataList);
        MyBubbleSort test = new MyBubbleSort();
        test.sort();
        System.out.println(dataList);
    }
}

package com.wang.demo.offer.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/10/24 7:12 上午
 */
public interface DataSort {

    // 3 1 4 5 2
    // 2 1 3 5 4
    List<Integer> dataList = new ArrayList<Integer>(){
        {
            add(3);
            add(4);
            add(1);
            add(2);
            add(5);
        }
    };

    void sort();

}

package com.wang.demo.offer.sort;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/10/24 8:06 上午
 */
public class MergeSort extends AbstractSort {

    @Override
    public void doSort(List<Integer> data) {
        int startIndex = 0;
        int endIndex = data.size() - 1;
        mergeSort(data, startIndex, endIndex);
    }

    private List<Integer> mergeSort(List<Integer> data, int startIndex, int endIndex) {
        int middle = startIndex / 2 + endIndex / 2;
        if (startIndex < endIndex) {
            mergeSort(data, startIndex, middle);
            mergeSort(data, middle + 1, endIndex);
            data = doMergeSort(data, startIndex, endIndex, middle);
        }
        return data;
    }

    private List<Integer> doMergeSort(List<Integer> data, int start, int end, int middle) {
        List<Integer> result = new LinkedList<>();
        int index1 = start;
        int index2 = middle + 1;
        while (index1 <= middle && index2 <= end) {
            if (data.get(index1) <= data.get(index2)) {
                result.add(data.get(index1));
                index1++;
            } else {
                result.add(data.get(index2));
                index2++;
            }
        }

        if (index1 <= middle) {
            while (index1 <= middle) {
                result.add(data.get(index1));
                index1++;
            }
        }

        if (index2 <= end) {
            while (index2 <= end) {
                result.add(data.get(index2));
                index2++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        DataSort sort = new MergeSort();
//        sort.sort();
//        System.out.println(dataList);
        List<String> list = new LinkedList<String>(){
            {
                add("String");
            }
        };
        Map<String,Object> map = new HashMap<String,Object>(){
            {
                put("test", String.class);
            }
        };
        System.out.println(list.getClass().getSuperclass());
        System.out.println(((ParameterizedType)list.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        System.out.println(((ParameterizedType)map.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);

    }
}

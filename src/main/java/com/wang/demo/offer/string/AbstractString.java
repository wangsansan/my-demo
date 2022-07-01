package com.wang.demo.offer.string;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/1 8:45 上午
 */
public class AbstractString {

    protected static void reverse(char[] array, int startIndex, int endIndex) {
        int i = startIndex, j = endIndex;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
    }

    protected static void swap(char[] array, int index1, int index2) {
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}

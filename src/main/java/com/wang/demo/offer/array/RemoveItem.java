package com.wang.demo.offer.array;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/15 7:53 上午
 */
public class RemoveItem extends AbstractArray {


    public static int solution(int[] array, int item) {
        if (array == null) {
            return 0;
        }

        int faster = 0, slower = 0;
        for (; faster < array.length; faster++) {
            if (array[faster] != item) {
                array[slower++] = array[faster];
            }
        }

        return slower;
    }

    public static void main(String[] args) {
        System.out.println(solution(array, 2));

    }

}

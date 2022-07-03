package com.wang.demo.offer.twoPointer;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/3 9:06 上午
 */

import org.apache.commons.lang3.ArrayUtils;

/**
 * 从某个数组里remove掉指定的val
 * 返回数组的新长度和数组
 * 空间复杂度是O(1)
 */
public class RemoveVal {

    public static int solution(int[] array, int val) {
        if (ArrayUtils.isEmpty(array)) {
            return 0;
        }

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != val) {
                array[j++] = array[i];
            }
        }

        // 注意，此处要输出的是长度，不用减一
        return j;
    }

    public static void main(String[] args) {
        int[] array = {3,2,2,3,4};
        int val = 3;
        System.out.println(solution(array, val));
    }

}

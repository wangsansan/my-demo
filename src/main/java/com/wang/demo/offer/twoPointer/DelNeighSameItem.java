package com.wang.demo.offer.twoPointer;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/6 8:12 上午
 */

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 删除字符串中的所有相邻重复项，直到不能再删除
 */
public class DelNeighSameItem {

    public static String solution(String str) {
        if (StringUtils.isBlank(str) || str.length() == 1) {
            return str;
        }

        char[] array = str.toCharArray();
        int front = 1;
        int back = 0;
        List<Character> result = new ArrayList<>();
        while (front < array.length) {
            if (array[front] == array[back]) {
                front++;
            } else {
                if (front - back == 1) {
                    result.add(array[back]);
                }
                back = front;
                front = back + 1;
            }
        }
        if (back == array.length - 1) {
            result.add(array[back]);
        }


        String value = result.stream().map(String::valueOf).collect(Collectors.joining());
        if (value.length() == str.length()) {
            return value;
        } else {
            return solution(value);
        }
    }

    public static void main(String[] args) {
        String str = "abbaaaca";
        System.out.println(solution(str));
    }

}

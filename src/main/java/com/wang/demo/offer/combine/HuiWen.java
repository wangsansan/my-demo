package com.wang.demo.offer.combine;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/3 8:13 上午
 */

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 把一个字符串分割，找出分割出来的所有回文字符串
 */
public class HuiWen {

    private static List<String> result = new LinkedList<>();

    private static StringBuilder path = new StringBuilder();

    public static void solution(String s) {
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            process(array, i);
        }
        result = result.stream().distinct().collect(Collectors.toList());
    }

    private static void process(char[] array, int index) {
        if (isHuiWen()) {
            result.add(path.toString());
        }
        if (index == array.length) {
            return;
        }
        path.append(array[index]);
        process(array, index + 1);
        path.deleteCharAt(path.length() - 1);
    }

    private static boolean isHuiWen() {
        if (StringUtils.isBlank(path)) {
            return false;
        }
        int index1 = 0;
        int index2 = path.length() - 1;
        while (index1 < index2) {
            if (path.charAt(index1) != (path.charAt(index2))) {
                return false;
            }
            index1++;
            index2--;
        }

        return true;
    }

    public static void main(String[] args) {
        String str = "acba";
        solution(str);
        System.out.println(result);
    }

}

package com.wang.demo.offer.hash;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/6/23 8:08 上午
 */


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 判断两个字符串是不是字母异位词
 */
public class JudgeString {

    public static boolean solution(String str1, String str2) {
        if (StringUtils.length(str1) != StringUtils.length(str2)) {
            return false;
        }

        /**
         * 两个空字符串，此处认为返回false
         */
        if (StringUtils.length(str1) == 0) {
            return false;
        }

        Map<Character, Integer> record = Maps.newHashMap();
        for (char c : str1.toCharArray()) {
            if (Objects.isNull(record.get(c))) {
                record.put(c, 1);
            } else {
                record.put(c, record.get(c) + 1);
            }
        }

        for (char c : str2.toCharArray()) {
            Integer count = record.get(c);
            if (Objects.isNull(count)) {
                return false;
            }
            if (count == 1) {
                record.remove(c);
            } else {
                record.put(c, count - 1);
            }
        }

        return MapUtils.isEmpty(record);

    }

    public static void main(String[] args) {
        String str1 = "wang";
        String str2 = "wangg";
        System.out.println(solution(str1, str2));
    }

}

package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/21 8:57 上午
 */

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
 * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 */
public class SameStructureString {

    public static boolean solution(String org, String target) {
        // 此处不判断是否为null了
        /**
         * 注意此处要设置双向映射，如果只设置一个，可能会出现
         * org里多个字符对应target里同一个字符，且无法辨别出来的情况
         */
        Map<Character, Character> mapping1 = Maps.newHashMapWithExpectedSize(org.length());
        Map<Character, Character> mapping2 = Maps.newHashMapWithExpectedSize(org.length());

        for (int i = 0; i < org.length(); i++) {
            char orgChar = org.charAt(i);
            char targetChar = target.charAt(i);
            if (Objects.isNull(mapping1.get(orgChar))) {
                mapping1.put(orgChar, targetChar);
            }
            if (Objects.isNull(mapping2.get(targetChar))) {
                mapping2.put(targetChar, orgChar);
            }
            if (mapping1.get(orgChar) != targetChar || mapping2.get(targetChar) != orgChar) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("egg", "add"));
        System.out.println(solution("foo", "bar"));
        System.out.println(solution("title", "paper"));
    }

}

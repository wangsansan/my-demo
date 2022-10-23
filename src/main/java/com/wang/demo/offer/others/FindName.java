package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/23 8:56 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 长按键入:
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 */
public class FindName {

    public static boolean solution(String name, String typed) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(typed)) {
            return false;
        }
        if (typed.length() < name.length()) {
            return false;
        }
        char pre = '0';
        int index1 = 0, index2 = 0;
        while (index1 < name.length()) {
            // 如果typed已经匹配完，name还没匹配完，返回false
            if (index2 >= typed.length()) {
                return false;
            }
            char c1 = name.charAt(index1);
            char c2 = typed.charAt(index2);
            if (c2 == c1) {
                pre = c1;
                index1++;
                index2++;
            } else if (c2 == pre) {
                index2++;
            } else {
                return false;
            }
        }
        // name已经匹配完，typed的剩余字符应该都是name的最后一个字符
        if (index2 < typed.length() - 1) {
            for (int i = index2; i < typed.length(); i++) {
                if (typed.charAt(i) != name.charAt(name.length() - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String name = "alex", typed = "aaleex";
        System.out.println(solution(name, typed));
        System.out.println(solution("saeed", "ssaaedd"));
        System.out.println(solution("leelee", "lleeelee"));
        System.out.println(solution("laiden", "laiden"));
    }

}

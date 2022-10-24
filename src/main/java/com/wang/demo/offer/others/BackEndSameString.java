package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/24 8:17 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 844.比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 */
public class BackEndSameString {

    public static boolean solution(String s, String t) {
        // 如果两者都为空字符串，此处返回false
        if (StringUtils.isBlank(s) || StringUtils.isBlank(t)) {
            return false;
        }
        String s1 = processString(s);
        String t1 = processString(t);
        return StringUtils.equals(s1, t1);
    }

    private static String processString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                stringBuilder.append(s.charAt(i));
            } else {
                if (stringBuilder.length() != 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static boolean solution1(String s, String t) {
        // 如果两者都为空字符串，此处返回false
        if (StringUtils.isBlank(s) || StringUtils.isBlank(t)) {
            return false;
        }
        int index1 = s.length() - 1, index2 = t.length() - 1;
        while (index1 >= 0 && index2 >= 0) {
            char sChar = s.charAt(index1);
            char tChar = t.charAt(index2);
            if (sChar == '#' || tChar == '#') {
                int count1 = 0;
                int tmpIndex1 = index1;
                while (s.charAt(tmpIndex1--) == '#') {
                    count1++;
                }
                int count2 = 0;
                int tmpIndex2 = index2;
                while (t.charAt(tmpIndex2--) == '#') {
                    count2++;
                }
                index1 -= 2 * count1;
                index2 -= 2 * count2;
            } else if (sChar == tChar) {
                index1--;
                index2--;
            } else {
                return false;
            }

        }
        if (index1 > 0) {
            String substring = s.substring(0, index1 + 1);
            if (StringUtils.isNotBlank(processString(substring))) {
                return false;
            }
        }

        if (index2 > 0) {
            String subString = t.substring(0, index2 + 1);
            if (StringUtils.isNotBlank(processString(subString))) {
                return false;
            }
        }

        return true;
    }

    public static boolean backspaceCompare(String s, String t) {
        int sSkipNum = 0; //记录s的#的个数
        int tSkipNum = 0; //记录t的#的个数
        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;
        while(true) {
            while(sIndex >= 0) { //每次记录连续的#并跳过被删除的字符
                if(s.charAt(sIndex) == '#') {
                    sSkipNum++;
                } else {
                    if(sSkipNum > 0) {
                        sSkipNum--;
                    } else {
                        break;
                    }
                }
                sIndex--;
            }
            while(tIndex >= 0) { //每次记录连续的#并跳过被删除的字符
                if(t.charAt(tIndex) == '#') {
                    tSkipNum++;
                } else {
                    if(tSkipNum > 0) {
                        tSkipNum--;
                    } else {
                        break;
                    }
                }
                tIndex--;
            }
            if(sIndex < 0 || tIndex < 0) { //s 或者 t遍历完了
                break;
            }
            if(s.charAt(sIndex) != t.charAt(tIndex)) { //当前下标的字符不相等
                return false;
            }
            sIndex--;
            tIndex--;
        }
        if(sIndex == -1 && tIndex == -1) { //同时遍历完 则相等
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution("ab#c","ad#c"));
        System.out.println(solution("ab##", "c#d#"));
        System.out.println(solution("a##c", "#a#c"));
        System.out.println(solution("a#c", "b"));
        System.out.println(solution1("ab#c","ad#c"));
        System.out.println(solution1("ab##", "c#d#"));
        System.out.println(solution1("a##c", "#a#c"));
        System.out.println(solution1("a#c", "b"));
        System.out.println(backspaceCompare("ab#c","ad#c"));
        System.out.println(backspaceCompare("ab##", "c#d#"));
        System.out.println(backspaceCompare("a##c", "#a#c"));
        System.out.println(backspaceCompare("a#c", "b"));
    }

}

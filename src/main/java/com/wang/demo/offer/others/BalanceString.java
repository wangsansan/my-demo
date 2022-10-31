package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/31 8:06 上午
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 1221. 分割平衡字符串
 * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
 * 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 * 注意：分割得到的每个字符串都必须是平衡字符串。
 * 返回可以通过分割得到的平衡字符串的 最大数量 。
 */
public class BalanceString {

    public static List<String> solution(String value) {
        if (Objects.isNull(value) || value.length() % 2 == 1) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            stringBuilder.append(c);
            if (c == 'L') {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                count = 0;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("RLRRLLRLRL"));
        System.out.println(solution("RLLLLRRRLR"));
        System.out.println(solution("LLLLRRRR"));
        System.out.println(solution("RLRRRLLRLL"));
    }

}

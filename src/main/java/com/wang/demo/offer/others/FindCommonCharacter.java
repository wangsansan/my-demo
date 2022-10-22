package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/22 9:18 上午
 */

import java.util.*;

/**
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：words = ["bella","label","roller"] 输出：["e","l","l"] 示例 2：
 * 输入：words = ["cool","lock","cook"] 输出：["c","o"]
 * 提示：
 * 1 <= words.length <= 100 1 <= words[i].length <= 100 words[i] 由小写英文字母组成
 */
public class FindCommonCharacter {

    public static String[] solution(String[] words) {
        if (Objects.isNull(words) || words.length == 0) {
            return new String[0];
        }
        int[][] mapping = new int[26][words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char[] chars = word.toCharArray();
            for (char aChar : chars) {
                int index = aChar - 'a';
                mapping[index][i]++;
            }
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            int min = mapping[i][0];
            for (int j = 1; j < words.length; j++) {
                if (mapping[i][j] < min) {
                    min = mapping[i][j];
                }
            }
            if (min == 0) {
                continue;
            } else {
                for (int k = 0; k < min; k++) {
                    list.add(String.valueOf((char)('a' + i)));
                }
            }

        }

        String[] result = new String[list.size()];
        return list.toArray(result);
    }

    public static void main(String[] args) {
        String[] words = {"bella","label","roller"};
        System.out.println(Arrays.toString(solution(words)));
        String[] words1 = {"cool","lock","cook"};
        System.out.println(Arrays.toString(solution(words1)));
    }

}

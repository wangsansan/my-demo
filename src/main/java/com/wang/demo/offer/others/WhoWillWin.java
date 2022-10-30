package com.wang.demo.offer.others;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/10/30 11:35 上午
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 649. Dota2 参议院
 */
public class WhoWillWin {

    public static final String Radiant = "Radiant";

    public static final char R = 'R';

    public static final String Dire = "Dire";

    public static final char D = 'D';

    public static String solution(String str) {
        if (str == null || str.length() == 0) {
            return StringUtils.EMPTY;
        }
        if (str.length() == 1) {
            if (str.startsWith("R")) {
                return Radiant;
            } else {
                return Dire;
            }
        }

        char[] values = str.toCharArray();
        for (int i = 0; i <= values.length; i++) {
            if (i ==  values.length) {
                i = -1;
                continue;
            }
            char value = values[i];
            if (value == R) {
                for (int j = i + 1; ; j++) {
                    if (j == values.length) {
                        j = 0;
                    }
                    if (j == i) {
                        return Radiant;
                    }
                    if (values[j] != D) {
                        continue;
                    }
                    values[j] = 'N';
                    break;
                }
            } else {
                if (value != D) {
                    continue;
                }
                for (int j = i + 1; ; j++) {
                    if (j == values.length) {
                        j = 0;
                    }
                    if (j == i) {
                        return Dire;
                    }
                    if (values[j] != R) {
                        continue;
                    }
                    values[j] = 'N';
                    break;
                }
            }

        }

        // 理论上不可能走到这里
        return Radiant;
    }

    public static void main(String[] args) {
        System.out.println(solution("RD"));
        System.out.println(solution("RDD"));
        System.out.println(solution("RRDDD"));
        System.out.println(solution("RDDRD"));
    }

}

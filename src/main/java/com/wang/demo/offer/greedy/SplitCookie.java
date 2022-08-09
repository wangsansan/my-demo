package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/9 7:59 上午
 */

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * 贪心算法专题：
 * 小朋友的胃口是g[],
 * 饼干大小是s[]
 * 求饼干最多能满足几个小朋友的胃口
 */
public class SplitCookie {

    public static int solution(int[] g, int[] s) {
        if (ArrayUtils.isEmpty(g) || ArrayUtils.isEmpty(s)) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int gEnd = g.length - 1;
        int sEnd = s.length - 1;
        int count = 0;
        while (gEnd >= 0 && sEnd >= 0) {
            if (s[sEnd] >= g[gEnd]) {
                count++;
                sEnd--;
            }
            gEnd--;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        System.out.println(solution(g, s));
    }

}

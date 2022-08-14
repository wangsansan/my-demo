package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/14 9:28 上午
 */

import org.apache.commons.lang3.ArrayUtils;

/**
 * 求出跳到数组最后一个位置最少步数
 */
public class JumpToEnd2 {

    public static int solution(int[] nums) {
        if (ArrayUtils.isEmpty(nums) || nums.length == 1) {
            return 0;
        }
        int step = 0;
        int nextMaxDistance = 0;
        int currentMaxDistance = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMaxDistance = Math.max(i + nums[i], nextMaxDistance);
            if (i == currentMaxDistance) {
                if (currentMaxDistance < nums.length - 1) {
                    step++;
                    //此时nextMaxDistance保存的是走到位置i之后，能走的最大距离
                    currentMaxDistance = nextMaxDistance;
                    if (currentMaxDistance >= nums.length - 1) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(solution(nums));
    }

}

package com.wang.demo.offer.greedy;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/8/20 9:13 上午
 */

import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个二维数组，points
 * points[i] = {xStart, xEnd}，如果射出的箭是x，xStart<=x<=xEnd，那么{xStart, xEnd}内的气球都能被击中
 * 求最少需要射击几次，可以射击全部气球
 */
public class ShootBalloon {

    public static int solution(List<Pair<Integer, Integer>> points) {
        if (CollectionUtils.isEmpty(points)) {
            return 0;
        }
        if (points.size() == 1) {
            return 1;
        }
        List<BalloonInfo> balloonList = points.parallelStream()
                .map(it -> new BalloonInfo(it.getKey(), it.getValue()))
                .sorted(Comparator.comparing(BalloonInfo::getStart))
                .collect(Collectors.toList());
        int count = 0;
        for (int i = balloonList.size() - 1; i >= 0; i--) {
            count++;
            BalloonInfo current = balloonList.get(i);
            current.shoot = true;
            if (i == 0) {
                break;
            }
            BalloonInfo before = balloonList.get(i - 1);
            if (hasSameSpace(before, current)) {
                before.shoot = true;
                i--;
            }
        }
        return count;
    }

    private static boolean hasSameSpace(BalloonInfo balloonInfo1, BalloonInfo balloonInfo2) {
        BalloonInfo smaller = balloonInfo1.start < balloonInfo2.start ? balloonInfo1 : balloonInfo2;
        BalloonInfo bigger = smaller == balloonInfo1 ? balloonInfo2 : balloonInfo1;
        if (smaller.end < bigger.start) {
            return false;
        }
        return true;
    }

    static class BalloonInfo {
        int start;

        int end;

        boolean shoot;

        public int getStart() {
            return start;
        }

        public BalloonInfo(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> param = new LinkedList<>();
        param.add(new Pair<>(10, 16));
        param.add(new Pair<>(2, 8));
        param.add(new Pair<>(1, 6));
        param.add(new Pair<>(7, 12));
        System.out.println(param);
        System.out.println(solution(param));
        param.clear();
        param.add(new Pair<>(1, 2));
        param.add(new Pair<>(3, 4));
        param.add(new Pair<>(5, 6));
        param.add(new Pair<>(7, 8));
        System.out.println(param);
        System.out.println(solution(param));
        param.clear();
        param.add(new Pair<>(1, 2));
        param.add(new Pair<>(2, 3));
        param.add(new Pair<>(3, 4));
        param.add(new Pair<>(4, 5));
        System.out.println(param);
        System.out.println(solution(param));
        param.clear();
        param.add(new Pair<>(1, 2));
        System.out.println(param);
        System.out.println(solution(param));
        param.clear();
        param.add(new Pair<>(2, 3));
        param.add(new Pair<>(2, 3));
        System.out.println(param);
        System.out.println(solution(param));
    }

}

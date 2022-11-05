package com.wang.demo.offer.others;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/4 8:19 上午
 */

/**
 * 841.钥匙和房间
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 */
public class KeyAndRoom {

    private static Set<Integer> path = Sets.newHashSet();

    public static boolean solution(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() <= 1) {
            return true;
        }
        int n = rooms.size();
        path.add(0);
        return process(rooms, 0, n);
    }

    private static boolean process(List<List<Integer>> rooms, int currentRoom, int n) {
        if (path.size() == n) {
            return true;
        }

        for (int i = 0; i < rooms.get(currentRoom).size(); i++) {
            int nextRoom = rooms.get(currentRoom).get(i);
            if (!path.add(nextRoom)) {
                continue;
            }
            boolean result = process(rooms, nextRoom, n);

            if (result) {
                return true;
            }

            /**
             * 本题不需要回溯，因为不是为了寻找一条路径
             */
//            path.remove(nextRoom);
        }

        return false;
    }

    public static void main(String[] args) {
//        List<List<Integer>> rooms = Arrays.asList(
//                Collections.singletonList(1),
//                Collections.singletonList(2),
//                Collections.singletonList(3),
//                Collections.singletonList(0));
//        System.out.println(solution(rooms));
//        path.clear();
        List<List<Integer>> rooms = Arrays.asList(
                Collections.singletonList(1),
                Collections.singletonList(2),
                Collections.singletonList(3),
                Arrays.asList(4, 5),
                Collections.singletonList(0),
                Collections.singletonList(0)
        );
        System.out.println(solution(rooms));
        path.clear();
        List<List<Integer>> rooms1 = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(3, 0, 1),
                Arrays.asList(2, 0),
                Collections.singletonList(0)
        );
        System.out.println(solution(rooms1));
    }

}

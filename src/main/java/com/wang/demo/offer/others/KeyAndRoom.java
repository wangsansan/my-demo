package com.wang.demo.offer.others;

import com.google.common.collect.Sets;

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

    public static boolean solution(int[][] rooms) {
        if (rooms == null || rooms.length <= 1) {
            return true;
        }
        int n = rooms.length;
        path.add(0);
        return process(rooms, 0, n);
    }

    private static boolean process(int[][] rooms, int currentRoom, int n) {
        if (path.size() == n) {
            return true;
        }

        for (int i = 0; i < rooms[currentRoom].length; i++) {
            int nextRoom = rooms[currentRoom][i];
            if (nextRoom == -1) {
                continue;
            }
            if (!path.add(nextRoom)) {
                continue;
            }
            boolean result = process(rooms, nextRoom, n);

            if (result) {
                return true;
            }

            path.remove(nextRoom);
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] rooms = {
                {-1, -1, -1, 1},
                {-1, -1, -1, 2},
                {-1, -1, -1, 3},
                {-1, -1, -1, 0}
        };
        System.out.println(solution(rooms));
        path.clear();
        int[][] rooms1 = {
                {-1, -1, 1, 3},
                {-1, 3, 0, 1},
                {-1, -1, 2, 0},
                {-1, -1, -1, 0}
        };
        System.out.println(solution(rooms1));
    }

}

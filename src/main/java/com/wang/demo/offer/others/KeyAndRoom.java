package com.wang.demo.offer.others;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/11/4 8:19 上午
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

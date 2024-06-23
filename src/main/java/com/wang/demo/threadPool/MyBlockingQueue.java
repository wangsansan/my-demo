package com.wang.demo.threadPool;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/23 07:50
 */
@Slf4j
@Data
public class MyBlockingQueue<T> {

    private Deque<T> tasks;

    private int capacity;

    private ReentrantLock lock = new ReentrantLock();

    private Condition empty = lock.newCondition();

    private Condition full = lock.newCondition();

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.tasks = new LinkedList<>();
    }

    public void add(T task) {
        lock.lock();
        try {
            // 防止假醒
            while (tasks.size() == capacity) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tasks.add(task);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T poll() {
        lock.lock();
        try {
            // 防止假醒
            while (CollectionUtils.isEmpty(tasks)) {
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = tasks.removeFirst();
            full.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public T poll(Long timeout, TimeUnit unit) {
        lock.lock();
        try {
            // 防止假醒
            long l = unit.toNanos(timeout);
            while (CollectionUtils.isEmpty(tasks)) {
                try {
                    if (l <= 0) {
                        return null;
                    } else {
                        l = empty.awaitNanos(l);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = tasks.removeFirst();
            full.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(T task) {
        lock.lock();
        try {
            // 防止假醒
            if (tasks.size() < capacity) {
                boolean added = tasks.add(task);
                empty.signal();
                return added;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public int size(){
        lock.lock();
        try {
            return tasks.size();
        } finally {
            lock.unlock();
        }
    }

}

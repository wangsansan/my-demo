package com.wang.demo.threadPool;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/23 07:46
 */
@Slf4j
public class MyThreadPool {

    private MyBlockingQueue<Runnable> queue;

    private Set<Worker> workers = new HashSet<>();

    private AtomicInteger workSize = new AtomicInteger(0);

    private int coreSize;

    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy rejectPolicy;

    public MyThreadPool(int queueSize, int coreSize, long timeout, TimeUnit timeUnit, RejectPolicy rejectPolicy) {
        this.queue = new MyBlockingQueue<>(queueSize);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    @AllArgsConstructor
    @Data
    class Worker extends Thread {

        private Runnable task;

        @Override
        public void run() {
            while (Objects.nonNull(task)
                    || (task = queue.poll(timeout, timeUnit)) != null) {
                task.run();
                task = null;
            }
            workers.remove(this);
        }
    }

    public void execute(Runnable task) {
        boolean add = false;
        if (workSize.get() < coreSize) {
            synchronized (this) {
                if (workSize.get() < coreSize) {
                    Worker worker = new Worker(task);
                    workers.add(worker);
                    workSize.getAndIncrement();
                    worker.start();
                    add = true;
                }
            }
        }
        if (!add) {
            boolean offer = queue.offer(task);
            if (!offer) {
                rejectPolicy.apply(task);
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(5, 2, 1L, TimeUnit.SECONDS, (task) -> {
            task.run();
        });
        for (int i = 0; i < 20; i++) {
            myThreadPool.execute(() -> {
                log.info("in my task");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("end task");
            });
        }
    }


}

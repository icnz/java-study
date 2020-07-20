package com.java.apps.concurrency.unsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 本类理解：
 * 使用线程池和信号量模拟有5000个客户端请求，同一时间内允许200个请求同时执行，每次执行时add()实现map请求计数，所有请求完成后打印计数。
 * 不安全现象：
 * 计数不稳定，不能正确实现计数功能。
 */
public class S02_Counter {
    private static final Logger logger = LoggerFactory.getLogger(S02_Counter.class);

    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;
    // 请求计数
    public static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量（当threadTotal为1时结果正确）
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(threadNum);
                    semaphore.release();
                } catch (Exception e) {
                    logger.error("exception", e);
                }
            });
        }
        executorService.shutdown();
        logger.info("size:{}", map.size());
    }

    private static void add(int threadNum) {
        map.put(threadNum, threadNum);
    }
}

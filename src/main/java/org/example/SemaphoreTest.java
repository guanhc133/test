package org.example;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private final Executor executor;

    private final Semaphore semaphore;

    public SemaphoreTest(Executor executor, int bound) {
        this.executor = executor;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(Runnable task) throws InterruptedException {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName()+"  信号量："+semaphore.availablePermits());
        try {
            try {
                executor.execute(task);
            } finally {
                semaphore.release();
            }
        } catch (Exception e) {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        SemaphoreTest test = new SemaphoreTest(executor,3);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            test.submitTask(new Thread(new Runnable() {
                @Override
                public void run() {
                }
            }));
        }
    }
}

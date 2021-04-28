package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    volatile static int count = 10;


    public static void main(String[] args) {
        CountDownLatch cd = new CountDownLatch(10);
        CountDownLatch cda = new CountDownLatch(1);

        for (int i = 0; i < 9; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        System.out.println("中断异常：" + e);
                    } finally {
                        cd.countDown();
                        System.out.println("还剩任务数："+ count--);
                    }
                }
            }).start();
        }
        cda.countDown();
        try {
            cda.await();
            System.out.println("执行开始------------");
            cd.await(1000,TimeUnit.MILLISECONDS);
            System.out.println("执行完毕------------");
        } catch (InterruptedException e) {
            System.out.println("线程超时：" + e);
        }

    }
}

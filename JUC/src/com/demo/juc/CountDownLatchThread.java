package com.demo.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch  控制次序减法
 * 猪跑完了，主人才发现
 *
 */
public class CountDownLatchThread {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("-------------" + finalI);
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "latch").start();
        }
        latch.await();
        System.out.println("------------- Fire Shoot!");
    }
}

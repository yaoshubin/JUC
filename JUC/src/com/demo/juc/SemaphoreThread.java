package com.demo.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore
 * acquire 占用
 * release 释放
 *  用于控制线程数
 */
public class SemaphoreThread {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"加入群聊！");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"退出微信群！");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}

package com.demo.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier  累加
 *  类似于拼团，够数才放行
 * --->|
 * --->|
 * --->|
 *
 */
public class CyclicBarrierThread {

    public static void main(String[] args) {
        CyclicBarrier  cyclicBarrier = new CyclicBarrier(10,()-> System.out.println("开幕"));
        for (int i = 0; i < 10; i++) {
           final int t = i;
            new Thread(() -> {
                try {
                    cyclicBarrier.getParties();
                    System.out.println("-------------倒计时:"+  t);
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

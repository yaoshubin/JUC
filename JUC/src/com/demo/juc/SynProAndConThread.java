package com.demo.juc;

/**
 * 用两个线程A，B 实现:分数 1,0,1,0... 交替出现
 * synchronize 初级版
 * 线程 操作 资源类:高内聚、低耦合
 * 判断、干活、通知
 *
 *
 */

public class SynProAndConThread {

    public static void main(String[] args) {
        Scorce score = new Scorce();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    score.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    score.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

    }
}

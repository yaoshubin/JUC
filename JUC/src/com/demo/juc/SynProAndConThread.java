package com.demo.juc;

/**
 * 线程交互：
 * 用两个线程A，B 实现:分数 1,0,1,0... 交替出现
 * synchronize 初级版:wait() / notify()
 * 线程 操作 资源类
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

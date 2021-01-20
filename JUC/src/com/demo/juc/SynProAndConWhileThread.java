package com.demo.juc;

/**
 * 用两个线程A，B ,C,D  多个线程实现:分数 1,0,1,0... 交替出现
 * synchronize 多个线程交互版出现线程虚唤
 * jdk1.8 官方说明：
 * 中断和虚假唤醒是可能的，并且wait方法应该始终在循环中使用：
 *   synchronized (obj) {
 *          while (<condition does not hold>)
 *              obj.wait();
 *          ... // Perform action appropriate to condition
 *      }
 *
 *  将if 改成while
 *
 */
public class SynProAndConWhileThread {

    public static void main(String[] args) {
        Scorce1 scorce1 = new Scorce1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    scorce1.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    scorce1.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    scorce1.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    scorce1.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

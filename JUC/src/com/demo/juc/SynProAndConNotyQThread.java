package com.demo.juc;

/**
 * 用两个线程A，B ,C,D  多个线程实现:分数 1,0,1,0... 交替出现
 * synchronize 多个线程交互版出现线程虚唤
 *  思考: 原理说明
 */
public class SynProAndConNotyQThread {

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

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    score.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    score.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

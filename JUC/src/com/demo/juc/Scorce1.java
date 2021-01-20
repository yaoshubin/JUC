package com.demo.juc;

/**
 * 资源类:
 *  高内聚、低耦合
 *  判断、干活、通知
 *  将if 改成while
 *
 */
class Scorce1 {

    private int number = 0;

    public synchronized void increase() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+":\t"+ number);
        this.notifyAll();
    }

    public synchronized void decrease() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":\t"+ number);
        this.notifyAll();
    }

}

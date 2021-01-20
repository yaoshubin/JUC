package com.demo.juc;

class Scorce {

    private int number = 0;

    public synchronized void increase() throws InterruptedException {
        if (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+":\t"+ number);
        this.notifyAll();
    }

    public synchronized void decrease() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":\t"+ number);
        this.notifyAll();
    }

}

package com.demo.juc;

/**
 *  用两个线程A，B 实现:分数 1,0,1,0... 交替出现
 *  Lock Condition 版
 *  官方解释：
 *  Lock替换synchronized方法和语句的使用， Condition  await()/ signal()取代了 wait ， notify和notifyAll 使用
 *  基本用法：
 *    class BoundedBuffer {
 *    final Lock lock = new ReentrantLock();
 *    final Condition notFull  = lock.newCondition();
 *    final Condition notEmpty = lock.newCondition();
 *
 *    final Object[] items = new Object[100];
 *    int putptr, takeptr, count;
 *
 *    public void put(Object x) throws InterruptedException {
 *      lock.lock(); try {
 *        while (count == items.length)
 *          notFull.await();
 *        items[putptr] = x;
 *        if (++putptr == items.length) putptr = 0;
 *        ++count;
 *        notEmpty.signal();
 *      } finally { lock.unlock(); }
 *    }
 *
 *    public Object take() throws InterruptedException {
 *      lock.lock(); try {
 *        while (count == 0)
 *          notEmpty.await();
 *        Object x = items[takeptr];
 *        if (++takeptr == items.length) takeptr = 0;
 *        --count;
 *        notFull.signal();
 *        return x;
 *      } finally { lock.unlock(); }
 *    }
 *  }
 */
public class LockConditionThread {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    counter.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    counter.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }

}

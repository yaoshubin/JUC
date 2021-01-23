package com.demo.juc;

import java.util.concurrent.TimeUnit;

/**
 * 线程8 锁问题彻底理解 static  和 synchronize 关键字
 * 秘诀：
 * 1、staic               锁Class
 * 2、synchronized        锁Object
 * <p>
 * 思考：
 * 1、同一个对象，同时加synchronized，先打印谁？
 * 2、同一个对象，同时加synchronized ，一个暂停3秒，先打印谁？
 * 3、同一个对象，一个synchronized 暂停3秒，一个无锁的方法，先打印谁？
 * 4、两个对象，同时加synchronized ，一个暂停3秒，先打印谁？
 * 5、同一个对象，两个static ，两个synchronized ，先打印谁？（暂停3秒）
 * 6、两个对象，两个static ，两个synchronized ，先打印谁？（暂停3秒）
 * 7、同一个对象，一个static ，两个synchronized ，先打印谁？（暂停3秒）
 * 8、两个对象，一个static ，两个 synchronized ，先打印谁？（暂停3秒）
 *
 * 运行结果：
 * 1、先sendEmail，后sendMsg
 * 2、先sendEmail，后sendMsg
 * 3、先print，后sendEmail
 * 4、先sendMsg,后sendEmail
 * 5、先sendEmail，后sendMsg
 * 6、先sendEmail，后sendMsg
 * 7、先sendMsg,后sendEmail
 * 8、先sendMsg,后sendEmail
 */

class Phone {

    public static  synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("------------sendEmail");
    }

    public  synchronized void sendMsg() {
        System.out.println("------------sendMsg");
    }

    public void print(){
        System.out.println("------------ print");
    }

}

public class Lock8Thread {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                // phone.sendMsg();
                 phone1.sendMsg();
                // phone.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

}

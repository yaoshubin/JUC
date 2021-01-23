package com.demo.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * (一)list 不安全故障分析：
 * 多个线程同时对list 进行读写操作时，会有多种结果:
 * 1、抛异常 java.util.ConcurrentModificationException
 * 2、翻车现场
 * [null, e14bd, e4bf3]
 * [null, e14bd, e4bf3]
 * 3、交通正常：
 * [21f46]
 * [21f46, 31e90]
 * [21f46, 31e90, da207]
 * <p>
 * <p>
 * (二)原因分析：
 * list  没有锁
 * <p>
 * （三）解决办法：
 * 线程安全list
 * 1、Vector
 * 2、加TT , Collections.synchronizedList(list);
 * 3、CopyOnWriteArrayList  -  NB
 *
 */
public class ListNoSafe {
    public static void main(String[] args) {
        //单个线程main 执行读操作
       /* List<String> list = Arrays.asList("1", "2", "3");
        list.forEach(System.out::println);*/

        //多个线程同时读和写
     /*   List<String> noSafeList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    noSafeList.add(UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(noSafeList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }*/

        //线程安全的集合类1-  Vector
       /* List<String> vector = new Vector<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    vector.add(UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(vector);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }*/


        //线程安全类2 - Collections.synchronizedList(list);
     /*   List<String> list = new ArrayList<>();
        List<String> synList = Collections.synchronizedList(list);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    synList.add(UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(synList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }*/

        // 线程安全的集合类高并发3-  CopyOnWriteArrayList
        List<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    copyOnWriteList.add(UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(copyOnWriteList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }


    }
}

package com.demo.juc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1、认清事实：HashSet 本质是HashMap
 * public HashSet() {
 *     map = new HashMap<>();
 * }
 * 2、如何理解Set 和Map （K,V )问题
 *  HashSet  的add 就是HashMap的put方法,看源码。
 *  K 是 add 传入参数，V  是一个Object 的常量
 *  public boolean add(E e) {
 *         return map.put(e, PRESENT)==null;
 *  }
 *   private static final Object PRESENT = new Object();
 *
 * 2、和list 对应的 方法，比较简单
 *
 */
public class SetUnfase {
    public static void main(String[] args) {
        //Set<String> set = new HashSet();
        //Set<String> cSet = Collections.synchronizedSet(set);
        //高并发下
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    set.add(UUID.randomUUID().toString().substring(0, 5));
                    System.out.println(set);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

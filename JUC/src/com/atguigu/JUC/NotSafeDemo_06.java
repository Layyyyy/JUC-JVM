package com.atguigu.JUC;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 题目 请举例说明集合类是不安全的
 * 1 故障现象
 *  java.util.ConcurrentModificationException
 *
 * 2 导致原因
 *   同一时刻多个线程同时对list写
 * 3 解决方案
 *   3.1 Vector
 *   3.2 Collections.synchronizedList(new ArrayList<>());
 *   3.3 CopyOnWriteArrayList
 * 4 优化建议(同样的错误，不出第二次)
 *
 */
public class NotSafeDemo_06 {
    public static void main(String[] args) {
        //ArrayList线程不安全
        //List<String> list = Arrays.asList("a","b","c");
       //list.forEach(System.out::println);
        //new ArrayList<String>();
        //new Vector<>();
        //Collections.synchronizedList(new ArrayList<>());
       // List<String> list = new CopyOnWriteArrayList<String>();

        //HashSet线程不安全
        //new HashSet<>()
        //Collections.synchronizedSet(new HashSet<>());
        //Set<String> set = new CopyOnWriteArraySet<String>();

        //HashMap线程不安全
        //new HashMap<>()
        //Collections.synchronizedMap(new HashMap<>())
        //ConcurrentHashMap<>()
        Map<String, String> map = new ConcurrentHashMap<>();

        for(int i=1;i<=30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
/**笔记
 * 写时复制
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器object[]添加，而是先将当前容器的object[]进行copy，
 * 复制出一个新的容器object[] newElements,然后新的容器Object[] newElements里添加元素，添加完元素后，再将原容器的引用指向新的
 * 容器setArray(newElement);这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以
 *CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 *    public boolean add(E e) {
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *             Object[] elements = getArray();
 *             int len = elements.length;
 *             Object[] newElements = Arrays.copyOf(elements, len + 1);
 *             newElements[len] = e;
 *             setArray(newElements);
 *             return true;
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 *
 * **/

/**
 * HashSet的底层是HashMap，那为什么HashSet的put的方法只传一个参数，而不是key，value两个参？
 * HashSet的put方法加进去的key ，value默认给一个 new Object（）；
 *
 * ArrayList 扩容是增加原来的一半
 * HashMap 扩容是增加一倍 2^4 ----> 2^5    数组 链表 红黑树
 *
 **/
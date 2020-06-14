package com.atguigu.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDate{
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    //用lock替代synchronize
    public  void increment() throws InterruptedException{
        lock.lock();
        try{
            //1判断
            while(number != 0){
                //this.wait();
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            //this.notifyAll();
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


/*    public synchronized void increment() throws InterruptedException{
        //1判断
        while(number != 0){
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException{
        //1判断
        while(number == 0){
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }*/

    public  void decrement() throws InterruptedException{
        lock.lock();
        try{
            //1判断
            while(number == 0){
                //this.wait();
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            //this.notifyAll();
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

/**
 * 题目 ：现在两个线程，可以操作初始化值为0的一个变量
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 实现交替，来10轮，变量初始值为0
 *
 * 1 高内聚低耦合的前提下， 线程操作资源类
 * 2 判断/干活/通知
 * 3 多线程交互中，必须要防止多线程的虚假唤醒，也即判断情况不能用if要用while
 */
public class ThreadWaitNotifyDemo_03 {
    public static void main(String[] args) throws Exception {
        ShareDate shareDate = new ShareDate();
        new Thread(()->{
            for (int i=0; i<10;i++){
                try{
                    shareDate.increment();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i=0; i<10;i++){
                try{
                    shareDate.decrement();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i=0; i<10;i++){
                try{
                    shareDate.increment();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i=0; i<10;i++){
                try{
                    shareDate.decrement();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

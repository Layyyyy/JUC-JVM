package com.atguigu.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class Ticket {
    private int number = 30;

    private Lock lock = new ReentrantLock();
    public synchronized void saleTicket(){
        lock.lock();
        try{
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           lock.unlock();
        }

    }
}

/**
 * 三个售票员卖出30张票
 * 多线程编程的企业级模板+套路
 *
 * 1  在高内聚低耦合的前提下，线程             操作(对外暴露的调用方法)                资源类
 */
public class SaleTicket_01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> { for (int i = 0; i<=30;i++)ticket.saleTicket();},"A").start();
        new Thread(() -> { for (int i = 0; i<=30;i++)ticket.saleTicket();},"B").start();
        new Thread(() -> { for (int i = 0; i<=30;i++)ticket.saleTicket();},"C").start();

        //Thread(Runnable target, String name)
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<=30;i++){
                    ticket.saleTicket();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<=30;i++){
                    ticket.saleTicket();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<=30;i++){
                    ticket.saleTicket();
                }
            }
        },"C").start();*/
    }
}
//1 实现Runnable接口重写run方法
//2 继承Thread类 实现run方法
//3 实现Callable接口，重写call(),利用FutureTask包装Callable，并作为task传入Thread构造函数；
//4 利用线程池
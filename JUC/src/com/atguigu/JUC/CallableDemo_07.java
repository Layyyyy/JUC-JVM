package com.atguigu.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("**********come in here");
        try{
            TimeUnit.SECONDS.sleep(4);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1024;
    }
}

/**实现Runable接口和实现Callable接口的不同
 *  0  Callable接口带泛型
 *  1  重写的方法不同
 *  2  实现Callable接口，重写的方法会抛异常
 *  3  实现Callable接口，重写的方法有返回值
*/
/**
 * 多线程中，第三种获取多线程的方式
 *  1 get方法一般请放在最后一行
 */
public class CallableDemo_07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread2());

        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        System.out.println(Thread.currentThread().getName()+"计算完成");

        System.out.println(futureTask.get());



    }
}

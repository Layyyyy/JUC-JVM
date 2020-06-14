package com.atguigu.JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo_08 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长走人");
    }


    public static void closeDoor(){
        for (int i = 1; i <= 6; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"\t班长走人");
    }
}

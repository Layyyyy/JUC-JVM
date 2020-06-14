package com.atguigu.JUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo_10 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟资源类，有3个车位

        for (int i = 1; i <= 6; i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t占到了车位");
                try{
                    TimeUnit.SECONDS.sleep(4);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
                System.out.println(Thread.currentThread().getName()+"\t离开了车位");
            },String.valueOf(i)).start();
        }

    }
}


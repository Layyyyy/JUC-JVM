package com.atguigu.JUC;

import java.util.concurrent.*;

public class MyThreadPoolDemo_13 {
    public static void main(String[] args) {
//线程池线程数配置
//cpu密集型 最大线程数 比cpu线程多1到2 io密集型 cpu线程数除阻塞系数
        System.out.println(Runtime.getRuntime().availableProcessors());
    ExecutorService threadPoll = new ThreadPoolExecutor(2,5,2L,TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());

        try{
            for(int i = 1;i <= 10;i++){
                threadPoll.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoll.shutdown();
        }


    }

    public static void initPool(){
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//指定线程池容量
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//线程池只有一个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩可扩容
        try{
            for(int i = 1;i <= 10;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}

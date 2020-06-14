package com.atguigu.JUC;

import java.util.concurrent.CompletableFuture;

public class CompletaFutureDemo_17 {
    public static void main(String[] args) {
        CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"没有返回值");
        });
    }
}

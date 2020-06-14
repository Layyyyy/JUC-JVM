package com.atguigu.JUC;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws Exception{
        //Thread.sleep(4000);
        try{
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("-----------sendEmail");
    }

    public  synchronized void sendSMS() throws Exception{
        System.out.println("------------sendSMS");
    }

    public void hello(){
        System.out.println("----------hello");
    }

}

/**
 * 多线程8锁
 * 1 标准访问，请问先打印邮件还是短信？ 邮件
 * 2 邮件方法暂停4秒钟，请问先打印邮件还是短信 邮件
 * 3 不打印短信 新增一个普通方法hello(),请问先打印邮件还是hello() hello
 * 4 两部手机，请问先打印邮件还是短信？ 短信
 * 5 两个静态同步方法，同一部手机，请问先打印邮件还是短信 邮件
 * 6 两个静态同步方法，两部手机，请问先打印邮件还是短信 邮件
 * 7 一个静态同步方法，一个普通同步方法，一部手机，请问先打印邮件还是短信 短信
 * 8 一个静态同步方法，一个普通同步方法，两部手机，请问先打印邮件还是短信 短信
 *
 * 笔记
 * 一个对象里面如果有多个synchronize方法，某一时刻内，只要有一个线程去调用其中的一个synchronize
 * 方法了，其他的线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronize方法
 * 锁的是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其他synchronize方法
 *
 * 加个普通方法后发现和同步锁无关
 * 换成两个对象后，就不是同一把锁了
 *
 * 所有的非静态同步方法用的都是同一把锁---实例对象本身
 *
 * synchronize实现同步的基础：Java中的每一个对象都可以作为锁
 * 具体表现为以下3种形式
 * 对于普通同步方法，锁的是当前实例对象
 * 对于静态同步方法，锁的是当前类的class对象
 * 对于同步方法块，锁的是synchronize括号里配置的对象
 *
 * 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须要释放锁
 *
 * 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁
 * 的方法释放锁后才能获取锁，但其他实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是
 * 不同的锁,所以无需等待该实例对象释放锁
 *
 * 所有的静态同步方法用的也是同一把锁-类对象本身
 * 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间不会有竟态条件的
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取
 * 锁，无论是同一个实例对象的静态同步方法之间，还是不同实例对象的静态同步方法之间，因为都是
 * 同一个类的实例
 *
 **/

public class Lock8_05 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try {
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(100);
        new Thread(()->{
            try {
                phone.sendSMS();
                //phone.hello();
                //phone2.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"B").start();
    }
}

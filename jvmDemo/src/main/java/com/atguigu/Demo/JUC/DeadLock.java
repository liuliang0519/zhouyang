package com.atguigu.Demo.JUC;

//死锁
// 俩把锁   A线程持有a锁 想拿b锁
//         B线程持有b锁 想拿a锁
//         结果相互僵持
public class DeadLock {


    public static void main(String[] args) {

        //创建俩个对象  相当于俩把锁
        Object o1 = new Object();
        Object o2 = new Object();


        //创建俩个线程
        new Thread(()->{
            synchronized (o1){
                System.out.println(Thread.currentThread().getName()+"线程 持有了o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName()+"线程，想要o2");
                }
            }
        },"A").start();


        new Thread(()->{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+"线程 持有了o2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName()+"线程，想要o1");
                }
            }
        },"B").start();



    }
}

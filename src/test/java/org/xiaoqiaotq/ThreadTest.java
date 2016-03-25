package org.xiaoqiaotq;

import java.util.concurrent.TimeUnit;

/**
 * author: will@ycode.cn
 * date  : 2016/3/14
 */
public class ThreadTest {
    private static volatile boolean stop=false;
    private static final Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {
        PrintThread printThread = new PrintThread();
        printThread.start();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    printThread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            TimeUnit.SECONDS.sleep(3);
            Thread notifyThread = new Thread(() -> {
                printThread.notify();
            });
            notifyThread.start();
            notifyThread.yield();
        }
    }
   static class PrintThread {
        int i=0;
        public void run() {
            if(!ThreadTest.stop){

                System.err.println(i++);
            }
        }
       public synchronized void start(){
               notify();
       }
       public synchronized void stop(){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}

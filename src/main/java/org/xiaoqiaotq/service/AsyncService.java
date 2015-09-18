package org.xiaoqiaotq.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/16
 */
@Service
public class AsyncService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Async
    public void aa(){
        try {
            System.err.println("------------start async-------"+Thread.currentThread().getName()+"-----");
            TimeUnit.SECONDS.sleep(3);
            System.err.println("------------stop async--------"+Thread.currentThread().getName()+"----");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Scheduled(fixedRate = 5000)
    public void time(){
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}

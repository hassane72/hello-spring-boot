package org.xiaoqiaotq.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/16
 */
@Service
public class AsyncService {
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
}

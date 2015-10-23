package org.xiaoqiaotq.service;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.xiaoqiaotq.domain.AutoDelLogTimeEntity;
import org.xiaoqiaotq.repository.AutoDelLogTimeRepository;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/16
 */
@Service
public class AsyncService  {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private AutoDelLogTimeRepository autoDelLogTimeRepository;
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
//    @Scheduled(fixedRate = 10000)
//    public void time(){
//        System.out.println("The time is now " + dateFormat.format(new Date()));
//    }

    public void saveAutoDelLogTime(AutoDelLogTimeEntity autoDelLogTime) {
        autoDelLogTimeRepository.save(autoDelLogTime);
    }

    @Scheduled(cron = "0 * * ? * MON-FRI")
    public void bb(){
        AutoDelLogTimeEntity time= autoDelLogTimeRepository.findTime();
        if(time!=null){
            System.err.println("The time is now " + time.getMonth());
        }else {
            System.err.println("no time set");
        }
    }

}

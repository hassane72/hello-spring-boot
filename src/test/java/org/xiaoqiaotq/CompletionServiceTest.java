package org.xiaoqiaotq;

import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/15
 */
public class CompletionServiceTest {
    @Test
    public void test2() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < 100; i++) {
            int num=i;
            new Thread(()-> {
                completionService.submit(()->{}, num + "aaaa");
            }).start();
        }
        while(true){
            String r = completionService.take().get();
            if (r != null){
                System.err.println(r);
            }
        }

    }
}

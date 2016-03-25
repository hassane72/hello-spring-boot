package org.xiaoqiaotq;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

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

    @Test
    public void testName() throws Exception {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4, 5);
        integerStream.forEach(integer -> {
            System.err.println(Thread.currentThread());
            System.err.println(integer);
        });
        integerStream2.parallel().forEach(integer -> {
            System.err.println(Thread.currentThread());
            System.err.println(integer);
        });

    }

    @Test
    public void test3() throws Exception {
        double a=0.06;
        double b=0.01;
        double c=a+b;
        System.err.println("result "+ c);
        System.out.println(0.06+0.01);
                 System.out.println(1.0-0.42);
                 System.out.println(4.015*100);
                 System.out.println(303.1/1000);
        BigDecimal d = BigDecimal.valueOf(121.31);
        System.err.println("adf");

    }

}

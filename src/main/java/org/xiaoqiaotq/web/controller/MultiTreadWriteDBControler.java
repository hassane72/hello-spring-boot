package org.xiaoqiaotq.web.controller;

import com.google.common.base.Stopwatch;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoqiaotq.domain.optimistic.Foo;
import org.xiaoqiaotq.repository.FooRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/20
 */
@RestController
public class MultiTreadWriteDBControler implements AsyncUncaughtExceptionHandler {
    @Autowired
    private TaskExecutor taskExecutor ;
    @Autowired
    private FooRepository fooRepository;
    private ExecutorService service= Executors.newCachedThreadPool();

    @Autowired
    private EntityManager em;

    @RequestMapping("/multiThread/test")
    public String tesaf(){
        taskExecutor.execute(()->{
           saveFoo2();
        });
        return "success";
    }
    @RequestMapping("/multiThread/test2")
    public String tesaf2(){
        service.submit(() -> {
            aa();
        });
        return "success";
    }
    @RequestMapping("/multiThread/test3")
    public String tesaf3(){
        new Thread(() -> {
            aa();
        }).start();
        return "success";
    }

    @RequestMapping("/multiThread/test4")
    public String tesaf4() {
        Future aa = aa();
        try {
            aa.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("-----------error happened-------");
            e.printStackTrace();
        }
        return "success";
    }
    public void saveFoo(){
        System.err.println(Thread.currentThread());
        Foo foo=new Foo();
        foo.setId(32l);
        foo.setThirdName("ds大师傅af");
        foo.setFirstName("威锋网");
        fooRepository.save(foo);
    }
    @Transactional
    public void saveFoo2(){
        System.err.println(Thread.currentThread());
        Foo foo=new Foo();
        foo.setId(32l);
        foo.setThirdName("ds大师傅af");
        foo.setFirstName("威锋网");
        em.persist(foo);
    }

    @Async
    public Future aa() {

        throw new RuntimeException("nnsdfasd");

//        System.err.println("---------start async------------------");
//        saveFoo2();
//        return new AsyncResult<Long>(3l);
    }

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        ex.printStackTrace();
        System.err.println(method);
    }
}

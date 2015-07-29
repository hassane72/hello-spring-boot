package org.xiaoqiaotq.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/8.
 */
@Aspect
@Component
public class HelloAspect {
    @Pointcut("execution(* org.xiaoqiaotq.service.*.*(..))")
    public void p1(){}

    @Around("p1()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("-------fffddd--------------");
        System.err.println(joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        return proceed;
    }
}

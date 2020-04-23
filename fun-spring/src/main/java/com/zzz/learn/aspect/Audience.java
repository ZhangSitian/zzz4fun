package com.zzz.learn.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Audience {

    @Pointcut("execution(* com.zzz.learn.interfaces.Performer.perform(..)) *")
    public void performance(){
    }

    @Before("performance() *")
    public void before(){
        System.out.println("Audience before");
    }

    @After("performance() *")
    public void after(){
        System.out.println("Audience after");
    }

    @AfterReturning("performance() *")
    public void afterReturn(){
        System.out.println("Audience afterReturn");
    }

    @AfterThrowing("performance() *")
    public void afterThrow(){
        System.out.println("Audience afterThrow");
    }

    @Around("performance() *")
    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("around start");
        long start = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("around error");
        }
        long end = System.currentTimeMillis();
        System.out.println("around end coast :"+(end-start));

    }

}

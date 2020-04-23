package com.zzz.learn.aspect;

import com.zzz.learn.interfaces.MindReader;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class Magician implements MindReader {
    private String thoughts;

    @Pointcut("execution(* com.zzz.learn.interfaces.Thinker.thinkOfSomething(String)) && args(thoughts)")
    public void thinking(String thoughts){}

    @Before("thinking(thoughts)")
    @Override
    public void interceptThoughts(String thoughts) {
        System.out.println("Intercepting volunteer's thoughts");
        this.thoughts = thoughts;
    }

    @Override
    public String getThoughts() {
        return thoughts;
    }
}

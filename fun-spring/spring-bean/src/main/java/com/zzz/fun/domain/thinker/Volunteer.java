package com.zzz.fun.domain.thinker;


import com.zzz.fun.interfaces.Thinker;

public class Volunteer implements Thinker {
    private String thoughts;

    @Override
    public void thinkOfSomething(String thoughts) {
        System.out.println("Volunteer thinkOfSomething");
        this.thoughts = thoughts;
    }

    public String getThoughts(){
        return thoughts;
    }
}

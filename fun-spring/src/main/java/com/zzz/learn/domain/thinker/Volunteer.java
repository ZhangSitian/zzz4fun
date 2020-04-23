package com.zzz.learn.domain.thinker;

import com.zzz.learn.interfaces.Thinker;

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

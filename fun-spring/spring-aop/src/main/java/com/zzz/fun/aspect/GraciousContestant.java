package com.zzz.fun.aspect;


import com.zzz.fun.interfaces.Contestant;

public class GraciousContestant implements Contestant {
    @Override
    public void receiveAward() {
        System.out.println("GraciousContestant receiveAward implements");
    }
}

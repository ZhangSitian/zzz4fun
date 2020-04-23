package com.zzz.learn.aspect;

import com.zzz.learn.interfaces.Contestant;

public class GraciousContestant implements Contestant {
    @Override
    public void receiveAward() {
        System.out.println("GraciousContestant receiveAward implements");
    }
}

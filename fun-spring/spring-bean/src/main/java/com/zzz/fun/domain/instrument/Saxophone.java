package com.zzz.fun.domain.instrument;


import com.zzz.fun.interfaces.Instrument;

public class Saxophone implements Instrument {
    @Override
    public void play() {
        System.out.println("TOOT TOOT TOOT");
    }
}

package com.zzz.learn.domain.instrument;

import com.zzz.learn.interfaces.Instrument;

public class Saxophone implements Instrument {
    @Override
    public void play() {
        System.out.println("TOOT TOOT TOOT");
    }
}

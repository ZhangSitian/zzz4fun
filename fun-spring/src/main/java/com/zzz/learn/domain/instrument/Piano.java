package com.zzz.learn.domain.instrument;

import com.zzz.learn.interfaces.Instrument;

public class Piano implements Instrument {
    @Override
    public void play() {
        System.out.println("PLINK PLINK PLINK");
    }
}

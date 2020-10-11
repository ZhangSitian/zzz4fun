package com.zzz.fun.domain.instrument;


import com.zzz.fun.interfaces.Instrument;

public class Piano implements Instrument {
    @Override
    public void play() {
        System.out.println("PLINK PLINK PLINK");
    }
}

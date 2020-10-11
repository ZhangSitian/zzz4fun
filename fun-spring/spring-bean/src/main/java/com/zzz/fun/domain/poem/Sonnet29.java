package com.zzz.fun.domain.poem;


import com.zzz.fun.interfaces.Poem;

public class Sonnet29 implements Poem {
    @Override
    public void recite() {
        System.out.println("poem: When in disgrace with fortune and men's eyes.");
    }
}

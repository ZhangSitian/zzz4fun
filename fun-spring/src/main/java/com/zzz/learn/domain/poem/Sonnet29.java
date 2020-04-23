package com.zzz.learn.domain.poem;

import com.zzz.learn.interfaces.Poem;

public class Sonnet29 implements Poem {
    @Override
    public void recite() {
        System.out.println("poem: When in disgrace with fortune and men's eyes.");
    }
}

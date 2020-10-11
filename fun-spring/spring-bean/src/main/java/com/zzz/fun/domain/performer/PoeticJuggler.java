package com.zzz.fun.domain.performer;

import com.zzz.fun.interfaces.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PoeticJuggler extends Juggler {
    @Autowired
    @Qualifier(value = "sonnet29")
    private Poem poem;

    private PoeticJuggler(){
        super();
    }

    private PoeticJuggler(Poem poem){
        super();
        this.poem = poem;
    }

    private PoeticJuggler(int beanBags, Poem poem){
        super(beanBags);
        this.poem = poem;
    }

    @Override
    public void perform() {
        super.perform();
        System.out.println("While reciting...");
        poem.recite();
    }

}

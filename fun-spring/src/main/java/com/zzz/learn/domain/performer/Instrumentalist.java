package com.zzz.learn.domain.performer;

import com.zzz.learn.interfaces.Instrument;
import com.zzz.learn.interfaces.Performer;


public class Instrumentalist implements Performer {
    private String song;
    private Instrument instrument;
    public Instrumentalist(){
    }
    @Override
    public void perform() {
        System.out.println("Playing "+song+":");
        instrument.play();
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Instrument getInstrument() {
        return instrument;
    }
}

package com.zzz.learn.domain;

import com.zzz.learn.interfaces.Instrument;

public class Values {
    private int intValue;
    private String message;
    private float floatValue;
    private String stringValue;
    private boolean booleanValue;
    private Instrument instrument;
    private String song;
    private Instrument otherInstrument;
    private double random;
    private Instrument someInstrument;
    private String someString;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Instrument getOtherInstrument() {
        return otherInstrument;
    }

    public void setOtherInstrument(Instrument otherInstrument) {
        this.otherInstrument = otherInstrument;
    }

    public double getRandom() {
        return random;
    }

    public void setRandom(double random) {
        this.random = random;
    }

    public Instrument getSomeInstrument() {
        return someInstrument;
    }

    public void setSomeInstrument(Instrument someInstrument) {
        this.someInstrument = someInstrument;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }
}

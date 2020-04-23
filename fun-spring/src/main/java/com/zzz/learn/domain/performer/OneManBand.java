package com.zzz.learn.domain.performer;

import com.zzz.learn.interfaces.Instrument;
import com.zzz.learn.interfaces.Performer;

import java.util.Collection;
import java.util.Map;

public class OneManBand implements Performer {
    private Collection<Instrument> instruments;

    private Map<String, Object> instrumentsMap;

    public OneManBand() {
    }

    @Override
    public void perform() {
        instruments.forEach(Instrument::play);
        instrumentsMap.forEach((key, value) -> {
            System.out.println(key + ":");
            if(value instanceof String){
                System.out.println(value);
            }else if(value instanceof Instrument){
                ((Instrument)value).play();
            }
        });
    }

    public void setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
    }

    public void setInstrumentsMap(Map<String, Object> instrumentsMap) {
        this.instrumentsMap = instrumentsMap;
    }

    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    public Map<String, Object> getInstrumentsMap() {
        return instrumentsMap;
    }
}

package com.solarexsoft.solarexhandler;

/**
 * Created by houruhou on 12/05/2017.
 */
public class SolarexMessage {
    SolarexHandler target;
    public int what;
    public Object obj;

    @Override
    public String toString() {
        return obj.toString();
    }
}

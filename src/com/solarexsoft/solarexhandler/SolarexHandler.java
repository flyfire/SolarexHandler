package com.solarexsoft.solarexhandler;

/**
 * Created by houruhou on 12/05/2017.
 */
public class SolarexHandler {

    private SolarexLooper mLooper;
    private SolarexMessageQueue mQueue;

    public SolarexHandler() {
        mLooper = SolarexLooper.myLooper();
        mQueue = mLooper.mQueue;
    }

    public void sendMessage(SolarexMessage msg) {
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    public void dispatchMessage(SolarexMessage msg) {
        //没实现Message.callback
        //没实现Handler.mCallback
        handleMessage(msg);
    }

    protected void handleMessage(SolarexMessage msg) {

    }
}

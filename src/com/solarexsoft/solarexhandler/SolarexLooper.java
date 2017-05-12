package com.solarexsoft.solarexhandler;

/**
 * Created by houruhou on 12/05/2017.
 */
public class SolarexLooper {

    static final ThreadLocal<SolarexLooper> sThreadLocal = new ThreadLocal<>();
    SolarexMessageQueue mQueue;

    public SolarexLooper() {
        mQueue = new SolarexMessageQueue();
    }

    /*
     * 创建loop对象
     */
    public static void prepare() {
        SolarexLooper looper = myLooper();
        if (looper != null) {
            throw new RuntimeException("Only one SolarexLooper can be crated per thread!");
        }
        sThreadLocal.set(new SolarexLooper());
    }

    public static SolarexLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        SolarexLooper looper = myLooper();
        if (looper == null) {
            throw new RuntimeException("No SolarexLooper, call prepare() first!");
        }
        SolarexMessageQueue queue = looper.mQueue;
        for (; ; ) {
            SolarexMessage msg = queue.next();
            if (msg == null) {
                continue;
            }
            msg.target.dispatchMessage(msg);
        }
    }
}

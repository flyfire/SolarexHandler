package com.solarexsoft.solarexhandler;

import java.util.UUID;

/**
 * Created by houruhou on 12/05/2017.
 */
public class TestHandler {
    public static void main(String args[]) {
        SolarexLooper.prepare();
        SolarexHandler handler = new SolarexHandler() {
            @Override
            protected void handleMessage(SolarexMessage msg) {
                super.handleMessage(msg);
                System.out.println("Thread: " + Thread.currentThread().getName() + ",received: " + msg);
            }
        };


        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SolarexMessage msg = new SolarexMessage();
                        msg.what = 1;
                        synchronized (UUID.class) {
                            msg.obj = UUID.randomUUID().toString();
                        }
                        System.out.println("Thread: " + Thread.currentThread().getName() + ",send: " + msg);
                        handler.sendMessage(msg);
                    }
                }
            }.start();
        }

        SolarexLooper.loop();
    }
}

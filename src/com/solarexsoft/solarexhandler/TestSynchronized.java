package com.solarexsoft.solarexhandler;

/**
 * Created by houruhou on 19/05/2017.
 */
public class TestSynchronized {

    public static void main(String[] args) {
        TestSynchronized test = new TestSynchronized();
        test.methodA();
        new Thread(new Test()).start();
    }

    static class Test implements Runnable {
        @Override
        public void run() {
            TestSynchronized test = new TestSynchronized();
            test.methodA();
        }
    }

    private synchronized void methodA() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + "@methodA");
        methodB();
    }

    private synchronized void methodB() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread:" + Thread.currentThread().getName() + "@methodB");
    }
}

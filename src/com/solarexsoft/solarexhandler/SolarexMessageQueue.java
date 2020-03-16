package com.solarexsoft.solarexhandler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by houruhou on 12/05/2017.
 */
public class SolarexMessageQueue {
    private ReentrantLock lock = new ReentrantLock();
    private Condition alreadyFull = lock.newCondition();
    private Condition alreadyEmpty = lock.newCondition();

    private SolarexMessage[] mMsgs;
    private int putIndex = 0;
    private int takeIndex = 0;
    private int count = 0;
    private final int MAX_SIZE = 5;

    public SolarexMessageQueue() {
        mMsgs = new SolarexMessage[MAX_SIZE];
    }

    public void enqueueMessage(SolarexMessage msg) {
        lock.lock();
        try {
            while (count == MAX_SIZE) {
                alreadyFull.await();
            }
            putIndex = putIndex == MAX_SIZE ? 0 : putIndex;
            mMsgs[putIndex++] = msg;
            count++;
            alreadyEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public SolarexMessage next() {
        SolarexMessage msg = null;
        lock.lock();
        try {
            while (count == 0) {
                alreadyEmpty.await();
            }
            takeIndex = takeIndex == MAX_SIZE ? 0 : takeIndex;
            msg = mMsgs[takeIndex];
            mMsgs[takeIndex] = null;
            takeIndex++;
            count--;
            alreadyFull.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return msg;
    }
}

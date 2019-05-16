package org.apt.primitive;

public class BaseSemaphore {

    private volatile boolean isLock = false;

    public synchronized void accure(){
        while (isLock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;
    }

    public synchronized void release() {
        isLock = false;
        notifyAll();
    }
}

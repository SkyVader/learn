package org.apt.primitive;

import java.util.concurrent.atomic.AtomicInteger;

public class PoolSemaphore extends BaseSemaphore{

    private AtomicInteger size;

    public PoolSemaphore(int sizePool){
        size = new AtomicInteger(sizePool);
    }

    public synchronized void accure() {
        while (size.get() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        size.decrementAndGet();
    }

    public synchronized void release() {
        size.getAndIncrement();
        notify();
    }
}

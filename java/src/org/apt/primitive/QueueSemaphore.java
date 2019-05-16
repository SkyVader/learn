package org.apt.primitive;

import java.util.LinkedList;
import java.util.Queue;

public class QueueSemaphore extends BaseSemaphore {

    private Queue<Thread> threadQueue = new LinkedList<>();
    private volatile boolean isLock = false;

    @Override
    public void accure() {
        threadQueue.add(Thread.currentThread());
        synchronized (this) {
            while (isLock || Thread.currentThread() != threadQueue.peek()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLock = true;
            threadQueue.remove();
        }
    }

    @Override
    public synchronized void release() {
        isLock = false;
        notifyAll();
    }
}

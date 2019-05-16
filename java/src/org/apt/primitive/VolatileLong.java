package org.apt.primitive;

public class VolatileLong {

    private volatile long data;
    private volatile boolean isModify =false;

    public VolatileLong(long data) {
        this.data = data;
    }

    public long value(){
        checkModify();
        return data;
    }

    public synchronized void modify(long newData) {
        this.data = newData;
        this.isModify = true;
        notify();
    }

    private synchronized void checkModify(){
        if(!isModify) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

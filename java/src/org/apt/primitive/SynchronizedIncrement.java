package org.apt.primitive;

public class SynchronizedIncrement {

    private int data;

    public SynchronizedIncrement(int data){
        this.data = data;
    }

    public int value() {
        return data;
    }

    public synchronized void inc() {
        this.data++;
    }
}

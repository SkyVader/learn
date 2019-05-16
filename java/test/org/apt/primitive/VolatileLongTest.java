package org.apt.primitive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VolatileLongTest {

    private int repeatCount = 10;

    @Test
    public void testReadWrite() throws InterruptedException {
        VolatileLong sharedObject = new VolatileLong(0L);
        Thread writer = new Thread(() -> {
            sharedObject.modify(10L);
        });
        Thread reader = new Thread(() -> {
            long v = sharedObject.value();
            sharedObject.modify(v + 10);
        });
        writer.start();
        reader.start();
        writer.join();
        reader.join();
        assertEquals(20L, sharedObject.value());
    }

    private void testWithRepeat(int repeatCount, Runnable f) throws InterruptedException {
        Thread[] threads = new Thread[repeatCount];
        for (int i = 0; i < repeatCount; i++) {
            threads[i] = new Thread(f);
            threads[i].start();
        }
        for (Thread thread: threads) thread.join();
    }

    private void modifySharedObject(VolatileLong sharedObject) {
        for (long i = 1; i <= repeatCount; i++) {
            sharedObject.modify(10L);
        }
    }
}

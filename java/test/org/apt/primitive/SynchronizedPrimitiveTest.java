package org.apt.primitive;


import org.junit.Assert;
import org.junit.Test;

public class SynchronizedPrimitiveTest {

    private static int repeatCount = 1000;

    @Test
    public void readWriteInt() throws InterruptedException {
        int originNumber = 10;
        SynchronizedIncrement volInt = new SynchronizedIncrement(originNumber);
        testWithRepeat(repeatCount, () -> volInt.inc());
        Assert.assertEquals(volInt.value(), originNumber+repeatCount);
    }

    private void testWithRepeat(int repeatCount, Runnable f) throws InterruptedException {
        Thread[] threads = new Thread[repeatCount];
        for (int i = 0; i < repeatCount; i++) {
            threads[i] = new Thread(f);
            threads[i].start();
        }
        for (Thread thread: threads) thread.join();
    }

}

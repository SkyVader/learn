package org.apt.primitive;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class BaseSemaphoreTest {

    @Test
    public void testRun() throws InterruptedException {
        BaseSemaphore semaphore = new BaseSemaphore();
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new TestThread(semaphore);
            ts[i].start();
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }
    }

    @Test
    public void testQueueSemaphore() throws InterruptedException {
        BaseSemaphore semaphore = new QueueSemaphore();
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new TestThread(semaphore);
            ts[i].start();
            Thread.sleep(10);
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }
    }

    @Test
    public void testPoolSemaphore() throws InterruptedException {
        PoolSemaphore semaphore = new PoolSemaphore(2);
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new TestThread(semaphore);
            ts[i].start();
            Thread.sleep(10);
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }
    }

    private class TestThread extends Thread {
        private BaseSemaphore semaphore;

        public TestThread(BaseSemaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            semaphore.accure();
            try {
                System.out.println(this.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}

package com.example.trace.threadlocal;

import org.junit.jupiter.api.Test;

public class ThreadLocalServiceTest {
    ThreadLocalService service = new ThreadLocalService();

    @Test
    void threadLocal() {
        System.out.printf("[%s] main start%n", this,getCurrentThreadName());

        final Runnable userA = () -> service.logic("userA");
        final Runnable userB = () -> service.logic("userB");

        final Thread threadA = new Thread(userA, "thread-A");
        final Thread threadB = new Thread(userB, "thread-B");

        threadA.start();
        this.sleep(1_00);
        threadB.start();

        this.sleep(2_000);
        System.out.printf("[%s] main exit%n", this,getCurrentThreadName());
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    private void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

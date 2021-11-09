package com.example.trace.threadlocal;

public class ThreadLocalService {
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(final String name) {
        System.out.printf("[%s] 저장 name=%s -> nameStore=%s%n", this.getCurrentThreadName(), name, this.nameStore.get());
        this.nameStore.set(name);

        this.sleep(1_000);

        System.out.printf("[%s] 조회 nameStore=%s%n", this.getCurrentThreadName(), this.nameStore.get());
        return this.nameStore.get();
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

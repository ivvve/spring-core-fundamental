package com.example.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        // 변하지 않는 부분
        final long startTime = System.currentTimeMillis();

        // 변하는 부분 : 추상화
        this.call();

        // 변하지 않는 부분
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    protected abstract void call();
}

package com.example.trace.strategy.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {
    public void execute(final Callback callback) {
        // 변하지 않는 부분
        final long startTime = System.currentTimeMillis();

        // 변하는 부분
        callback.call();

        // 변하지 않는 부분
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}

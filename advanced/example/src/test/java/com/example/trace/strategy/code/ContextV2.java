package com.example.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {
    public void execute(final Strategy strategy) {
        // 변하지 않는 부분
        final long startTime = System.currentTimeMillis();

        // 변하는 부분
        strategy.call();

        // 변하지 않는 부분
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}

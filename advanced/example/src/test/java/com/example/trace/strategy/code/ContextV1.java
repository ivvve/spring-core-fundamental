package com.example.trace.strategy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ContextV1 {
    private final Strategy strategy;

    public void execute() {
        // 변하지 않는 부분
        final long startTime = System.currentTimeMillis();

        // 변하는 부분
        this.strategy.call();

        // 변하지 않는 부분
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}

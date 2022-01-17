package com.example.trace.strategy;

import com.example.trace.strategy.code.ContextV1;
import com.example.trace.strategy.code.StrategyLogic1;
import com.example.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ContextV1Test {

    @Test
    void strategyV0() {
        this.logic1();
        this.logic2();
    }

    private void logic1() {
        // 변하지 않는 부분
        final long startTime = System.currentTimeMillis();

        // 변하는 부분
        log.info("비즈니스 로직1 실행");

        // 변하지 않는 부분
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        // 변하지 않는 부분
        final long startTime = System.currentTimeMillis();

        // 변하는 부분
        log.info("비즈니스 로직2 실행");

        // 변하지 않는 부분
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void strategyV1() {
        final StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        final ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        final StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        final ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    void strategyV2() {
        final StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        final ContextV1 context1 = new ContextV1(() -> System.out.println("비즈니스 로직1 실행"));
        context1.execute();

        final StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        final ContextV1 context2 = new ContextV1(() -> System.out.println("비즈니스 로직2 실행"));
        context2.execute();
    }
}

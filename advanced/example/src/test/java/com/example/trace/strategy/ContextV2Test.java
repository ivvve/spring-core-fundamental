package com.example.trace.strategy;

import com.example.trace.strategy.code.ContextV2;
import com.example.trace.strategy.code.StrategyLogic1;
import com.example.trace.strategy.code.StrategyLogic2;
import org.junit.jupiter.api.Test;

class ContextV2Test {
    @Test
    void strategyV1() {
        final ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        final ContextV2 context = new ContextV2();
        context.execute(() -> System.out.println("비즈니스 로직1 실행"));
        context.execute(() -> System.out.println("비즈니스 로직2 실행"));
    }
}

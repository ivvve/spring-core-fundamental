package com.example.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic2 implements Strategy {
    @Override
    public void call() {
        System.out.println("비즈니스 로직2 실행");
    }
}

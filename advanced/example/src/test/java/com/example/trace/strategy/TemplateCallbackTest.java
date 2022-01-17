package com.example.trace.strategy;

import com.example.trace.strategy.template.TimeLogTemplate;
import org.junit.jupiter.api.Test;

class TemplateCallbackTest {
    @Test
    void callbackV1() {
        final TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> System.out.println("비즈니스 로직1 실행"));
        timeLogTemplate.execute(() -> System.out.println("비즈니스 로직2 실행"));
    }
}

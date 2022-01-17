package com.example.trace.template;

import com.example.trace.template.code.AbstractTemplate;
import com.example.trace.template.code.SubClassLogic1;
import com.example.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateMethodTest {
    @Test
    void templateMethodV0() {
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
    void templateMethodV1() {
        final AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        final AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        final AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                System.out.println("비즈니스 로직1 실행");
            }
        };
        template1.execute();

        final AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                System.out.println("비즈니스 로직2 실행");
            }
        };
        template2.execute();
    }
}

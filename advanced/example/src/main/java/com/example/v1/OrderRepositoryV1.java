package com.example.v1;

import com.example.trace.TraceStatus;
import com.example.trace.hello.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(final String itemId) {
        final TraceStatus status = this.trace.begin("OrderRepositoryV1.save");

        try {
            if ("ex".equals(itemId)) {
                throw new IllegalStateException("예외 발생!");
            }

            // TODO 저장 로직
            this.sleep(1_000);

            this.trace.end(status);
        } catch (Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

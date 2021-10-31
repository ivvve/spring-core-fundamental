package com.example.v2;

import com.example.trace.TraceId;
import com.example.trace.TraceStatus;
import com.example.trace.hello.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;

    public void save(final TraceId priorTraceId, final String itemId) {
        final TraceStatus status = this.trace.beginSync(priorTraceId, "OrderRepositoryV2.save");

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

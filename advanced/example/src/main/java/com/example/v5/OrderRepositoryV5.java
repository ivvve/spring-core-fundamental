package com.example.v5;

import com.example.trace.LogTrace;
import com.example.trace.callback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {
    private final LogTrace logTrace;

    public void save(final String itemId) {
        new TraceTemplate(this.logTrace).execute("OrderRepositoryV5.save", () -> {
            if ("ex".equals(itemId)) {
                throw new IllegalStateException("예외 발생!");
            }

            // TODO 저장 로직
            sleep(1_000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

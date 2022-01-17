package com.example.v4;

import com.example.trace.LogTrace;
import com.example.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace logTrace;

    public void save(final String itemId) {
        final AbstractTemplate<Void> template = new AbstractTemplate<>(this.logTrace) {
            @Override
            protected Void call() {
                if ("ex".equals(itemId)) {
                    throw new IllegalStateException("예외 발생!");
                }

                // TODO 저장 로직
                sleep(1_000);
                return null;
            }
        };
        template.execute("RequiredArgsConstructor.save");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

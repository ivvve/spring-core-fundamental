package com.example.v3;

import com.example.trace.LogTrace;
import com.example.trace.TraceId;
import com.example.trace.TraceStatus;
import com.example.trace.hello.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(final TraceId priorTraceId, final String itemId) {
        final TraceStatus status = this.trace.begin("OrderServiceV2.orderItem");

        try {
            this.orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }
}

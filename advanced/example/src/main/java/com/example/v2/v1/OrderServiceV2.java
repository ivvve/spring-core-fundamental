package com.example.v2.v1;

import com.example.trace.TraceId;
import com.example.trace.TraceStatus;
import com.example.trace.hello.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(final TraceId priorTraceId, final String itemId) {
        final TraceStatus status = this.trace.beginSync(priorTraceId, "OrderServiceV2.orderItem");

        try {
            this.orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }
}

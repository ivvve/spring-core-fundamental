package com.example.v1;

import com.example.trace.TraceStatus;
import com.example.trace.hello.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId) {
        final TraceStatus status = this.trace.begin("OrderServiceV1.orderItem");

        try {
            this.orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            this.trace.exception(status, e);
            throw e;
        }
    }
}

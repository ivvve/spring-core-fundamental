package com.example.v5;

import com.example.trace.LogTrace;
import com.example.trace.callback.TraceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final LogTrace logTrace;

    public void orderItem(final String itemId) {
        new TraceTemplate(this.logTrace).execute("OrderServiceV5.orderItem", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}

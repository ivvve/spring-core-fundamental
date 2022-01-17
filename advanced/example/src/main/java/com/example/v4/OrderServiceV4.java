package com.example.v4;

import com.example.trace.LogTrace;
import com.example.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace logTrace;

    public void orderItem(final String itemId) {
        final AbstractTemplate<Void> template = new AbstractTemplate<>(this.logTrace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderServiceV4.orderItem");
    }
}
